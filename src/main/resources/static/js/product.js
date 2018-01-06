const vm = new Vue({
    el: '#app',
    data: {
        products: [],
        reviews: []
    },
    methods: {
        showReviews: function (code) {
            axios.get("/reviews?productCode=" + code + "&page=0&size=100")
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
        axios.get("/products?page=0&size=100")
            .then(response => {
                this.products = response.data.payload;
            });
    }
});
