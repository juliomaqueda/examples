const deepl = require('./deepl')

it('Happy path', () => {
    const fakeFetch = jest.fn().mockReturnValue(Promise.resolve({
        json: () => Promise.resolve({
            result: {
                translations: [
                    {
                        beams: [
                            {
                                postprocessed_sentence: 'dog'
                            }
                        ]
                    }
                ]
            }
        })
    }))

    deepl.getInEnglish('perro', fakeFetch)
        .then(result => expect(result).toBe('dog'))
})