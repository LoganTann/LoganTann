## dialogs

> label [enter] snippet test [tab] [tab] // [enter]

```js
monogatari.label("snippet test", "English", [
  //
]);
```

> say [tab] [tab] hello
> say [tab] e [tab] I'm eve

```js
monogatari.label("snippet test", "English", [
  //
  "narrator hello",
  "e I'm eve",
]);
```

> say: [tab] e [tab] confused [tab] I'm confused now

```js
monogatari.label("snippet test", "English", [
  //
  "narrator hello",
  "e I'm eve",
  "e:confused I'm confused now",
]);
```

# blocs

> choice [tab] Click here to do nothing [space] [tab] [tab]
> choiceb [tab] Click here to end [space] [tab] end [tab]

```js
{"Choice": {
  "Click here to do nothing ": {
    Text: "Click here to do nothing ",
    Do: "next",
  },
  "Click here to end ": {
    Text: "Click here to end ",
    Do: "end",
  },

}},

```

> timer [tab] 3000 [tab] console.log("hard choice..."); [tab]
> [up] [end] [ret] [suppr] [down] [down] [beg]

```js
{"Choice": {
  "Click here to do nothing ": {
    Text: "Click here to do nothing ",
    Do: "next",
  },
  "Click here to end ": {
    Text: "Click here to end ",
    Do: "end",
  },
  "Timer": {
    time: 3000,
    callback() {
      console.log("Hard choice...");
      return Promise.resolve();
    }
  }  
}},

```
