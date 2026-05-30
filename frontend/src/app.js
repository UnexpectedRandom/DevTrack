/**
 * DevTrack — UI wiring only (no API calls yet).
 * Connect fetch to http://localhost:8080/api when backend + CORS are ready.
 */

const API_BASE = "http://localhost:8080/api";

const authView = document.getElementById("auth-view");
const appView = document.getElementById("app-view");
const toastRegion = document.getElementById("toast-region");

document.querySelectorAll(".auth-tab").forEach((tab) => {
  tab.addEventListener("click", () => {
    const targetId = tab.id === "tab-signin" ? "panel-signin" : "panel-register";

    document.querySelectorAll(".auth-tab").forEach((t) => {
      t.classList.toggle("is-active", t === tab);
      t.setAttribute("aria-selected", t === tab ? "true" : "false");
    });

    document.querySelectorAll(".auth-panel").forEach((panel) => {
      const show = panel.id === targetId;
      panel.classList.toggle("is-active", show);
      panel.hidden = !show;
    });
  });
});

function showApp() {
  authView.classList.add("is-hidden");
  appView.classList.remove("is-hidden");
}

function showAuth() {
  appView.classList.add("is-hidden");
  authView.classList.remove("is-hidden");
}

document.getElementById("form-signin")?.addEventListener("submit", (e) => {
  e.preventDefault();
  showApp();
  toast("Signed in (UI preview — not connected to API).");
});

document.getElementById("form-register")?.addEventListener("submit", (e) => {
  e.preventDefault();
  const name = document.getElementById("register-name")?.value;
  const email = document.getElementById("register-email")?.value;
  if (name) document.getElementById("display-user-name").textContent = name;
  if (email) document.getElementById("display-user-email").textContent = email;
  showApp();
  toast("Account created (UI preview — wire to POST /api/createUser).");
});

document.getElementById("btn-signout")?.addEventListener("click", () => {
  showAuth();
});

const views = document.querySelectorAll(".view");
const navLinks = document.querySelectorAll(".nav-link[data-view]");

function switchView(viewName) {
  views.forEach((v) => {
    const match = v.classList.contains(`view-${viewName}`);
    v.classList.toggle("is-active", match);
    v.hidden = !match;
  });

  navLinks.forEach((link) => {
    link.classList.toggle("is-active", link.dataset.view === viewName);
  });
}

navLinks.forEach((link) => {
  link.addEventListener("click", (e) => {
    e.preventDefault();
    switchView(link.dataset.view);
  });
});

document.querySelectorAll('a[href="#applications"]').forEach((a) => {
  if (a.classList.contains("nav-link")) return;
  a.addEventListener("click", (e) => {
    e.preventDefault();
    switchView("applications");
  });
});

function openDialog(id) {
  const dialog = document.getElementById(id);
  if (dialog && typeof dialog.showModal === "function") {
    dialog.showModal();
  }
}

function closeDialog(dialog) {
  if (dialog && dialog.open) dialog.close();
}

document.querySelectorAll("[data-open-dialog]").forEach((btn) => {
  btn.addEventListener("click", () => openDialog(btn.dataset.openDialog));
});

document.querySelectorAll("[data-close-dialog]").forEach((btn) => {
  btn.addEventListener("click", () => {
    const dialog = btn.closest("dialog");
    closeDialog(dialog);
  });
});

document.querySelectorAll(".modal").forEach((dialog) => {
  dialog.addEventListener("click", (e) => {
    if (e.target === dialog) closeDialog(dialog);
  });
});

document.getElementById("form-add-application")?.addEventListener("submit", (e) => {
  e.preventDefault();
  closeDialog(document.getElementById("dialog-add-application"));
  toast("Application saved (UI preview — wire to POST /api/createResource).");
});

document.getElementById("form-edit-application")?.addEventListener("submit", (e) => {
  e.preventDefault();
  closeDialog(document.getElementById("dialog-edit-application"));
  toast("Application updated (UI preview — wire to PUT /api/updateResource).");
});

document.getElementById("form-confirm-delete")?.addEventListener("submit", (e) => {
  e.preventDefault();
  closeDialog(document.getElementById("dialog-confirm-delete"));
  toast("Application deleted (UI preview — wire to DELETE /api/deleteResource/{RID}).");
});

document.getElementById("applications-tbody")?.addEventListener("click", (e) => {
  const btn = e.target.closest("[data-action]");
  if (!btn) return;

  const rid = btn.dataset.rid;
  const row = btn.closest("tr");
  const company = row?.querySelector("strong")?.textContent ?? "this application";

  if (btn.dataset.action === "edit") {
    document.getElementById("edit-rid").value = rid;
    document.getElementById("edit-company").value = company;
    const salaryText = row?.querySelector(".cell-mono")?.textContent?.replace(/[^\d]/g, "") ?? "";
    document.getElementById("edit-salary").value = salaryText;
    openDialog("dialog-edit-application");
  }

  if (btn.dataset.action === "delete") {
    document.getElementById("delete-rid").value = rid;
    document.getElementById("delete-confirm-message").textContent =
      `Remove "${company}"? This cannot be undone.`;
    openDialog("dialog-confirm-delete");
  }
});

function toast(message) {
  if (!toastRegion) return;
  const el = document.createElement("div");
  el.className = "toast";
  el.textContent = message;
  toastRegion.appendChild(el);
  requestAnimationFrame(() => el.classList.add("is-visible"));
  setTimeout(() => {
    el.classList.remove("is-visible");
    setTimeout(() => el.remove(), 300);
  }, 3200);
}

// Expose for later API integration
window.DevTrack = { API_BASE, showApp, showAuth, toast, switchView };
