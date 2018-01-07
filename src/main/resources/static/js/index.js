const vm = new Vue({
    el: '#app',
    data: {
        code: '',
        processStepText: '',
        parses: [],
        reviews: [],
        showProgress: false
    },
    methods: {
        preParse: function () {
            let that = this;
            setTimeout(function () {
                that.processStepText = "Transforming Data..";
            }, 500);
            this.showProgress = true;
            this.processStepText = "Extracting Data..";
            setTimeout(function () {
                that.processStepText = "Loading Data..";
            }, 1000);
            setTimeout(function () {
                that.parse()
            }, 1500);
        },
        parse: function () {
            axios.get("parse/code/" + this.code)
                .then(response => {
                    const payload = response.data.payload;
                    this.parses.unshift(payload);
                    this.showProgress = false;
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
