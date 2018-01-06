const vm = new Vue({
    el: '#app',
    data() {
        return {
            code: '',
            parses: [],
            reviews: []
        }
    },
    methods: {
        parse: function () {
            axios.get("parse/code/" + this.code)
                .then(response => {
                    const payload = response.data.payload;
                    this.parses.unshift(payload);
                });

        },
        showReviews: function (parseId) {
            axios.get("/reviews?parseEntryId=" + parseId + "&page=0&size=100")
                .then(response => {
                    this.reviews = response.data.payload;
                })
        },
        truncate: function () {
          axios.delete("/truncate")
              .then(response => {
                location.reload();
              })
        }
    },
    created() {
        axios.get("/parse?page=0&size=100")
            .then(response => {
                this.parses = response.data.payload;
            })
    }
});
