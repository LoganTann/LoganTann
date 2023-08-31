'use babel';

import MonogatariActionsCompletionProvider from './complete-actions';
import MonogatariApiCompletionProvider from './complete-API';
import ProjectWatcher from './projectWatcher';
import AtomMonogatariView from './atom-monogatari-view';
import { CompositeDisposable } from 'atom';
import express from 'express';

export default {

  atomMonogatariView: null,
  modalPanel: null,
  subscriptions: null,

  activate(state) {
    ProjectWatcher.activate();

    this.atomMonogatariView = new AtomMonogatariView(state.atomMonogatariViewState);
    this.modalPanel = atom.workspace.addModalPanel({
      item: this.atomMonogatariView.getElement(),
      visible: false
    });

    // Events subscribed to in atom's system can be easily cleaned up with a CompositeDisposable
    this.subscriptions = new CompositeDisposable();

    // Register command that toggles this view
    this.subscriptions.add(atom.commands.add('atom-workspace', {
      'atom-monogatari:toggle': () => this.toggle()
    }));
    this.subscriptions.add(atom.commands.add('atom-workspace', {
      'atom-monogatari:startDevServer': () => this.startDevServer()
    }));
  },

  deactivate() {
    ProjectWatcher.destroy();
    this.modalPanel.destroy();
    this.subscriptions.dispose();
    this.atomMonogatariView.destroy();
  },

  serialize() {
    return {
      atomMonogatariViewState: this.atomMonogatariView.serialize()
    };
  },

  toggle() {
    return (
      this.modalPanel.isVisible() ?
      this.modalPanel.hide() :
      this.modalPanel.show()
    );
  },

  server: null,
  port: 3000,
  startDevServer() {
    if (!ProjectWatcher.projectDir) {
      throw "Cannot start the server, because the extension couldn't find a valid monogatari project";
    }
    const folder = ProjectWatcher.projectDir.getPath();
    const server = express();

    server.use(express.static(ProjectWatcher.projectDir.getPath()));

    server.listen(this.port, () => {
      console.log(`Monogatari local server listening listening at http://localhost:${this.port} -> ${folder}`)
    });
    this.server = server;
  },

  getProvider() {
    return [MonogatariActionsCompletionProvider, MonogatariApiCompletionProvider];
  }

};
