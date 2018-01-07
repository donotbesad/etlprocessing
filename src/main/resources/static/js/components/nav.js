Vue.component('top-navbar', {
    template: `    <nav class="navbar navbar-expand-lg navbar-default">
        <a class="navbar-brand" href="#">Turbo parse</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item active">
                    <a class="nav-link" href="/">Parse</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/products">Products</a>
                </li>
            </ul>
            <button v-on:click="truncate" type="button" class="nav-link btn btn-danger">Delete all data</button>
        </div>
    </nav>
`,
    methods: {
        truncate: function () {
            axios.delete("/truncate")
                .then(response => {
                    location.reload();
                })
        }
    }
});