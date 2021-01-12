listOf(
        ":app",

        ":features:home",
        ":features:activity_feature",
        ":features:fragment_feature",

        ":navigation",
        ":design",
        ":core"
).forEach {
    include(it)
}
