module.exports = {
    url: 'https://www.deepl.com/jsonrpc',
    dataForWord: word => {
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
    },
    extractTranslation: parsedResponse => parsedResponse
        .result
        .translations[0]
        .beams[0]
        .postprocessed_sentence
}