module.exports = {
	base_url: 'https://www.google.es/search?q=',
	base_element: '#ires',
	base_childs: 'li.g',
	elems_per_page: 100,
	cur_page: 0,
	search_key: undefined,
	search_url: undefined,

	get_next_url: function() {
		return this.base_url + this.search_key + this.__get_page_query();
	},

	__get_page_query: function() {
		return '&num=' + this.elems_per_page + '&start=' + (this.cur_page++ * this.elems_per_page);
	},

	get_elements: function($) {
		return $(this.base_element).find(this.base_childs);
	},

	find: function(elem) {
		var found = false;
		var links = elem.find('h3 a');
		var domain = this.search_url;

		links.each(function(i, elem) {
			var url_linked = elem.attribs.href.match(/^.*?q=([^&]+).*/);

			if(url_linked) {
				url_linked = url_linked[1].replace(/\/$/, '');

				if(domain === url_linked) {
					found = true;
					return false;
				}
			}
		});


		return found;
	},

	reset: function() {
		this.cur_page = 0;
		this.search_key = undefined;
	}
}
