Vue.filter('date', function (value) {
    return value[0] + "/" + value[1] + "/" + value[2];
});

Vue.filter('recommended', function (value) {
    return "row card border-" + (value ? "success" : "danger") + " mb-3";
});

Vue.filter('array', function (value) {
    return value.toString();
});

