'use babel';

import { File } from 'atom';

class monogatariProjectWatcher {
  constructor() {

  }
  activate() {
    this.projectDir = this.locateMonogatariProject();
    // todo : nice message instead of a brutal throw and handle project reload
    if (!this.projectDir) throw "Unable to locate a monogatari game in the currently opened pane.";
    this.projectobserver = atom.project.onDidChangeFiles(events => {
      if (events[0].action === "modified" && this.projectDir.contains(events[0].path)) {
        // regarding the use case of this class, this is not necessary to notify a batch of changes.
        this.notifyObserver("modified", events[0].path);
      }
    });
    this.registerObserver("modified", this.onProjectUpdate);
  }

  onProjectUpdate(arg) {
    //
  }

  projectDir = null;

  destroy() {
    this.projectobserver.dispose();
    this.observerCollection = {};
    this.projectDir = null;
  }

  locateMonogatariProject() {
    for (const dir of atom.project.getDirectories()) {
      if (isMonogatariProject(dir)) {
        return dir;
      }
    }
    return null;
  }


  // --- OBSERVER PATTERN ---
  observerCollection = {}
  registerObserver(eventType, callback) {
    if (typeof this.observerCollection[eventType] === "undefined") {
      this.observerCollection[eventType] = [];
    }
    this.observerCollection[eventType].push(callback);
  }
  unregisterObserver(eventType, callback) {
    this.checkObservableEvent(eventType);
    this.observerCollection[eventType] = this.observerCollection[eventType].filter(
      (item) => (item !== fn) ? item : undefined
    );
  }
  notifyObserver(eventType, args) {
    this.checkObservableEvent(eventType);
    const calledFunctions = [];
    for (const observer of this.observerCollection[eventType]) {
      calledFunctions.push(observer(args));
    }
    return Promise.all(calledFunctions);
  }
  checkObservableEvent(eventType) {
    if (typeof this.observerCollection[eventType] === "undefined") {
      throw `The observable event ${eventType} is not defined`;
    }
  }
}

/**
 * Private: checks if the directory is a monogatari project
 * A dir is a monogatari project if it contains index.html and some js files used by the framework.
 * todo : make it editable
 */
function isMonogatariProject(dir) {
  const toSearch = [
    "/index.html",
    "/js/main.js",
    "/js/options.js",
    "/js/storage.js"
  ];
  const dirPath = dir.getPath();
  for (const filename of toSearch) {
    const file = new File(dirPath + filename);
    if (! file.existsSync()) return false;
  }
  return true;
}

export default new monogatariProjectWatcher();
