module.exports = {
	base_url: 'https://www.bing.com/search?q',
	base_element: '#b_results',
	base_childs: 'li.b_algo',
	elems_per_page: 10,
	cur_page: 0,
	search_key: undefined,
	search_url: undefined,

	get_next_url: function() {
		return this.base_url + this.search_key + this.__get_page_query();
	},

	__get_page_query: function() {
		return '&first=' + (this.cur_page++ * this.elems_per_page + 1);
	},

	get_elements: function($) {
		return $(this.base_element).find(this.base_childs);
	},

	find: function(elem) {
		var found = false;
		var links = elem.find('h2 a');
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
	}
}
