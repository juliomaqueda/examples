const fetch = require('node-fetch')
const Bacon = require('baconjs')
const deepl = require('./deepl')

function getInEnglish(word) {
	const promise = fetch(
		deepl.url, { 
			method: 'POST',
			body:    JSON.stringify(deepl.dataForWord(word)),
			headers: { 'Content-Type': 'application/json' }
		})
		.then(asJson)
		.then(deepl.extractTranslation)
		.catch(err => console.error(err));
	
	const stream = Bacon.fromPromise(promise)
	return stream
}

const asJson = response => response.json()
const stream = new Bacon.Bus()

stream
	.flatMap(word => getInEnglish(word))
	.map(word => word.toUpperCase())
	.onValue(word => console.log(word))

stream.push('coche')
stream.push('miel')
stream.push('trompeta')