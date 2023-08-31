'use babel';

class MonogatariApiCompletionProvider {
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
			"monogatari.run()",
			"monogatari.storage()",
			"monogatari.registerComponent()",
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
export default new MonogatariApiCompletionProvider();
