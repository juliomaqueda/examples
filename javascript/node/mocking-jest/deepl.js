const url = 'https://www.deepl.com/jsonrpc'

const dataForWord = word => {
	return {
		"method": "LMT_handle_jobs",
		"params": {
			"jobs": [
				{
					"raw_en_sentence": word
				}
			],
			"lang": {
				"source_lang_computed": "ES",
				"target_lang": "EN"
			}
		}
	}
}

const asJson = response => response.json()

const extractTranslation = parsedResponse => parsedResponse
		.result
		.translations[0]
		.beams[0]
		.postprocessed_sentence

function getInEnglish(word, fetch) {
	return fetch(
		url, { 
			method: 'POST',
			body: JSON.stringify(dataForWord(word)),
			headers: { 'Content-Type': 'application/json' }
		})
		.then(asJson)
		.then(extractTranslation)
		.catch(err => console.error(err));
}

module.exports = {
	getInEnglish
}