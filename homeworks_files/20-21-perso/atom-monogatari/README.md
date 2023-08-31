# atom-monogatari package

This package adds support for the [Monogatari framework](https://monogatari.io) (a Visual Novel development tool powered by HTML5 and Javascript) to the Atom text editor.

![atom-monogatari](https://user-images.githubusercontent.com/28659185/147580329-2993a540-bf91-4dba-9e1d-95ba9e67f957.gif)


In addition to Atom's built-in git integration, the package will extends the text editor with :
- Snippets and local server (unfinished)
- context-aware autocompletion (currently at a very very basic stage)
- and the ability to run your game inside atom instead of your browser (planned, proof of concept).

... almost making Atom to become a full-featured Integrated Development Environment for Monogatari, while staying very begginer friendly

This package is inspired from the [language-renpy](https://github.com/renpy/language-renpy) and [autocomplete-love](https://github.com/bschulte/LOVE-Autocomplete)

Contributions are greatly appreciated, I created this package just under a surge of motivation, and therefore, I don't plan to actively maintain it.

Please fork this repository and open a pull request to add snippets, make grammar tweaks, etc.

### Installation

This package has been tested only in Windows 10, although it should work in other platforms like Ubuntu or OSX.

**Since this package is currently under development, it's not published to the atom package registry, and you'll have to install it manually**.

Make sure Atom and Git are correctly installed. Then open PowerShell and type the following commands :

```powershell
cd "$HOME\.atom\packages"
git clone https://github.com/LoganTann/atom-monogatari

# to fetch updates
# cd "$HOME\.atom\packages\atom-monogatari"
# git pull
```

## Features

### Snippets (Work in progress)

This extension provides basic snippets to speed up the process of writing VN scripts.
To see available snippets, go to the package settings (`Settings > Packages > atom-monosgatari > Snippets`)

The usage is done with keywords followed by a `Tab` to insert the snippet.

Note : the snippets uses a particular code-style that may be different from the documentation or yours.

To-do :
- Some snippets are left unfinished
- Find a way to create unit tests ?

### Advanced Autocompletion (Work in progress)

Some Monogatari actions have the potential to benefit a context-aware, just like Intellisense in VS Code. For example, the `show` action takes in first argument the type of media to show (scene, character...) and in second argument the asset ID, both of them cannot be suggested with simple snippets.

It's an unfinished work, you may get wrong suggestions

### Syntax highlighting (Planned)

Just as explained in the section above, most actions are constructed by multiple category of terms (media type, asset ID...), which can be shown in different colors.

This is a planned work, and it's currently not implemented (due to the difficulty to create a proper parser).

### Local development server (Work in progress, Proof of concept)

The local server can be started in the `menu > packages > atom-monogatari > start dev server` or by pressing `ctrl-shift-p` then `Atom Monogatari : StartDevServer`

Working with a local server is optional, but it's necessary for some features (asset preloading with Service Workers, dynamic asset loading, pixel shading/editing in canvas)

An Atom package is extremely similar to a NPM package, because it is run in a NodeJS instance.
Therefore, this is possible to include an ExpressJS server directly into the extension.

I don't plan to integrate a live (or hot) reload server.

### in-editor Browser (Planned, Proof of concept)

Since Atom is a web app that runs in Electron, and its API gives access to the editor's DOM, it might be possible to execute a Monogatari game directly inside a tab next to your code.

I only plan to try it as a proof of concept, and I cannot even warranty I will make this feature.

## FAQ

### People still use Atom ?

Read this excellent comment from this reddit thread : https://www.reddit.com/r/typescript/comments/ph79px/comment/hbijd83/?utm_source=share&utm_medium=web2x&context=3

TLDR : It's true that Visual Studio Code is faster, more suited for large code-base project and the package ecosystem is much larger.   
While VS Code seems to be the choice for most people, I personnaly think that Atom allows to packages more control over the editor.

Moveover, Atom is currently used by my team and we don't need to change our tools, since the editor and the git integration is more beginner-friendly. 

Even I use VS Code for most of my project, because intelligent autocompletion is better than what atom surpasses : code writing experience.
