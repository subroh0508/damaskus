import kotlinx.browser.document

fun main() {
    document.querySelector("h1")?.textContent = "Example Page with Kotlin/JS"
    document.body?.style?.backgroundColor = "blue"
}
