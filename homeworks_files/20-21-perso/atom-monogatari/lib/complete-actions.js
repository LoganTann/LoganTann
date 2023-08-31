'use babel';

class MonogatariActionsCompletionProvider {
	constructor() {
		// offer suggestions when editing any file type
		this.selector = '.source.js';
		this.disableForSelector =  '.source.js .comment';
	}

	getSuggestions(options) {
		const { prefix } = options;
		return this.findMatchingSuggestions(prefix);
	}

	findMatchingSuggestions(prefix) {
		suggestions = [
			"show particles",
			"show background",
			"show image",
			"show scene",
			"show video",
			"show notification",
		];
		// filter list of words to those with a matching prefix
		let matchingWords = suggestions.filter((suggestion) => {
			return suggestion.startsWith(prefix) && prefix && /^\S*$/.test(prefix);
		});

		// convert the array of words into an array of objects with a text property
		let matchingSuggestions = matchingWords.map((word) => {
			return {
				text: word,
				description: "Context-aware autocompletion is unfinished..."
			};
		});

		return matchingSuggestions;
	}
}
export default new MonogatariActionsCompletionProvider();
