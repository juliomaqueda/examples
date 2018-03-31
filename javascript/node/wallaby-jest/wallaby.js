module.exports = function () {
	return {
		files: [
			'order-total.js'
		],
		
		tests: [
			'*.spec.js'
		],
		
		env: {
			type: 'node',
			runner: 'node'
		},

		testFramework: 'jest'
	};
};