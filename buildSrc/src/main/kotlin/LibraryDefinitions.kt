interface Library : ConfigurableDependency {
    val groupName: String
    val name: String
    val version: String

    override fun asString(): String {
        return "$groupName:$name:$version"
    }
}

/**
 * Declare a simple library.
 */
abstract class BaseLibrary(
    override val groupName: String,
    override val name: String,
    override val version: String
) : Library

/**
 * Declare a group of libraries.
 */
abstract class LibraryGroup(
    val groupName: String,
    val version: String
)

/**
 * Declare a library that is part of a library group.
 */
abstract class LibraryGroupChild(
    val group: LibraryGroup,
    override val name: String,
    override val version: String = group.version
) : Library {
    override val groupName: String get() = group.groupName
}
