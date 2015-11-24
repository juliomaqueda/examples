var cheerio = require('cheerio');
var request = require('request');

var s_engine, keywords = [], time_start, time_download;

var search_properties = {
	cur_pos: 0,
	located: false,
	error: false,
	max_elements: 50,

	can_check_items: function() {
		return (!this.located && !this.error && (this.cur_pos < this.max_elements));
	}
};

function getHTML(err, resp, html) {
	//console.log('Descarga realizada en ' + ((new Date().getTime()) - time_download) + ' ms');

	if(err || resp.statusCode != 200) {
		console.error('No ha podido recuperarse el documento: ' + err);
		search_properties.error = 'No ha podido recuperarse el documento: ' + err;
	}

	var $ = cheerio.load(html);

	var list = s_engine.get_elements($);

	if(list && list.length > 0) {
		list.each(function(i, elem) {
			search_properties.cur_pos++;

			if(!search_properties.can_check_items())
				return false;

			search_properties.located = s_engine.find($(elem));

			return !search_properties.located;
		});
	}
	else
		search_properties.error = 'No elements found';


	if(search_properties.located) {
		console.log(
			'Su url se encuentra en la posicion ' + search_properties.cur_pos +
			' (' + ((new Date().getTime()) - time_start) + ' ms)'
		);

		searchNextKey();
	}
	else {
		if(search_properties.can_check_items()) {
			time_download = new Date().getTime();
			request(s_engine.get_next_url(), getHTML);
		}
		else {
			console.log(
				'Su pagina no ha sido encontrada en los ' + search_properties.cur_pos + ' primeros resultados' +
				' (' + ((new Date().getTime()) - time_start) + ' ms)'
			);

			searchNextKey();
		}
	}
}

function searchNextKey() {
	if(keywords.length > 0) {
		var cur_key = keywords.pop();

		search_properties.cur_pos = 0;

		s_engine.reset();
		s_engine.search_key = cur_key;

		console.log('Buscando clave: ' + cur_key);

		time_start = new Date().getTime();
		time_download = new Date().getTime();

		request(s_engine.get_next_url(), getHTML);
	}
	else
		console.log('FIN');
}

function beginSearch() {
	if(keywords.length > 0) {
		console.log('INICIO');

		searchNextKey();
	}
	else {
		console.log('Sin clave');
		return false;
	}
}

function getPageKeywords(err, resp, html) {
	if(err || resp.statusCode != 200) {
		console.error('No ha podido recuperarse el documento: ' + err);
		search_properties.error = 'No ha podido recuperarse el documento: ' + err;
	}

	var $ = cheerio.load(html);

	var meta_keywords = $('head meta[name=keywords]');

	if(meta_keywords.length > 0) {
		var keywords_text = meta_keywords[0].attribs.content;

		if(keywords_text) {
			console.log('Keywords encontradas: ' + keywords_text);

			keywords_text = keywords_text.replace(/\s*,\s*/g, ',').replace(/\s/g, '+');
			keywords = keywords_text.split(',').reverse();
		}
	}

	beginSearch();
}

module.exports = function (search_url, search_key) {
	search_url = search_url.replace(/\/$/, '').replace(/^www/, 'http://www');

	console.log('Analizando url: ' + search_url);

	var search_engines = require('./engines');

	s_engine = search_engines.google;

	s_engine.search_url = search_url;

	if(search_key) {
		keywords.push(search_key);
		beginSearch();
	}
	else
		request(search_url, getPageKeywords);
}
