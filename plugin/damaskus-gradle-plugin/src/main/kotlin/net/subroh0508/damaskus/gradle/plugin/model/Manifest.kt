package net.subroh0508.damaskus.gradle.plugin.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Manifest(
    @SerialName("manifest_version")
    val manifestVersion: Int,
    val name: String,
    val version: String,
    val action: Action? = null,
    @SerialName("default_locale")
    val defaultLocale: String? = null,
    val description: String? = null,
    val icons: Icon? = null,
    val author: String? = null,
    val automation: Automation? = null,
    @SerialName("chrome_settings_overrides")
    val chromeSettingsOverrides: ChromeSettingsOverride? = null,
    @SerialName("chrome_url_overrides")
    val chromeUrlOverride: ChromeUrlOverride? = null,
    val commands: Map<String, Command>? = null,
    @SerialName("content_scripts")
    val contentScripts: List<ContentScript>? = null,
    @SerialName("content_security_policy")
    val contentSecurityPolicy: ContentSecurityPolicy? = null,
    @SerialName("cross_origin_embedder_policy")
    val crossOriginEmbedderPolicy: CrossOriginEmbedderPolicy? = null,
    @SerialName("cross_origin_opener_policy")
    val crossOriginOpenerPolicy: CrossOriginOpenerPolicy? = null,
    @SerialName("declarative_net_request")
    val declarativeNetRequest: DeclarativeNetRequest? = null,
    @SerialName("devtools_page")
    val devtoolsPage: String? = null,
    @SerialName("event_rules")
    val eventRules: List<EventRule>? = null,
    val export: Export? = null,
    @SerialName("externally_connectable")
    val externallyConnectable: ExternallyConnectable? = null,
    @SerialName("file_browser_handlers")
    val fileBrowserHandlers: List<FileBrowserHandler>? = null,
    @SerialName("file_system_provider_capabilities")
    val fileSystemProviderCapabilities: FileSystemProviderCapabilities? = null,
    @SerialName("homepage_url")
    val homepageUrl: String? = null,
    val import: List<Import>? = null,
    val incognito: String? = null,
    @SerialName("input_components")
    val inputComponents: List<InputComponent>? = null,
    val key: String? = null,
    @SerialName("minimum_chrome_version")
    val minimumChromeVersion: String? = null,
    val oauth2: OAuth2? = null,
    val omnibox: Omnibox? = null,
    @SerialName("optional_host_permissions")
    val optionalHostPermissions: List<String>? = null,
    @SerialName("optional_permissions")
    val optionalPermissions: List<String>? = null,
    @SerialName("options_page")
    val optionsPage: String? = null,
    @SerialName("options_ui")
    val optionsUi: OptionsUi? = null,
    val permissions: List<String>? = null,
    val requirements: List<Requirement>? = null,
    val sandbox: Sandbox? = null,
    @SerialName("short_name")
    val shortName: String? = null,
    val storage: Storage? = null,
    @SerialName("tts_engine")
    val ttsEngine: TtsEngine? = null,
    @SerialName("update_url")
    val updateUrl: String? = null,
    @SerialName("version_name")
    val versionName: String? = null,
    @SerialName("web_accessible_resources")
    val webAccessibleResources: List<WebAccessibleResource>? = null,
) {
    @Serializable
    data class Action(
        @SerialName("default_icon")
        val defaultIcon: Icon? = null,
        @SerialName("default_title")
        val defaultTitle: String? = null,
        @SerialName("default_popup")
        val defaultPopup: String? = null,
    ) {
        @Serializable
        data class Icon(
            @SerialName("16")
            val icon16: String? = null,
            @SerialName("24")
            val icon24: String? = null,
            @SerialName("32")
            val icon32: String? = null,
        )
    }

    @Serializable
    data class Icon(
        @SerialName("16")
        val icon16: String? = null,
        @SerialName("32")
        val icon32: String? = null,
        @SerialName("48")
        val icon48: String? = null,
        @SerialName("128")
        val icon128: String? = null,
    )

    @Serializable
    data class Automation(
        val desktop: Boolean,
        val interact: Boolean,
        val matches: List<String>,
    )

    @Serializable
    data class ChromeSettingsOverride(
        val homepage: String,
        @SerialName("search_provider")
        val searchProvider: SearchProvider,
        @SerialName("startup_pages")
        val startupPages: List<String>,
    ) {
        @Serializable
        data class SearchProvider(
            val name: String,
            val keyword: String,
            @SerialName("search_url")
            val searchUrl: String,
            @SerialName("favicon_url")
            val faviconUrl: String,
            @SerialName("suggest_url")
            val suggestUrl: String,
            @SerialName("instant_url")
            val instantUrl: String,
            @SerialName("image_url")
            val imageUrl: String,
            @SerialName("search_url_post_params")
            val searchUrlPostParams: String,
            @SerialName("suggest_url_post_params")
            val suggestUrlPostParams: String,
            @SerialName("instant_url_post_params")
            val instantUrlPostParams: String,
            @SerialName("image_url_post_params")
            val imageUrlPostParams: String,
            @SerialName("alternate_urls")
            val alternateUrls: List<String>,
            val encoding: String,
            @SerialName("is_default")
            val isDefault: Boolean,
        )
    }

    @Serializable
    sealed class ChromeUrlOverride {
        @Serializable
        data class Bookmarks(val bookmarks: String) : ChromeUrlOverride()
        @Serializable
        data class History(val history: String) : ChromeUrlOverride()
        @Serializable
        data class Newtab(val newtab: String) : ChromeUrlOverride()
    }

    @Serializable
    data class Command(
        @SerialName("suggested_key")
        val suggestedKey: SuggestedKey,
        val description: String? = null,
    ) {
        companion object {
            const val EXECUTE_ACTION = "_execute_action"
        }

        @Serializable
        data class SuggestedKey(
            val default: String,
            val mac: String? = null,
            val linux: String? = null,
            val windows: String? = null,
            val chromeos: String? = null,
        )
    }

    @Serializable
    data class ContentScript(
        val matches: List<String>,
        val css: List<String>? = null,
        val js: List<String>? = null,
        @SerialName("run_at")
        val runAt: String? = null,
        @SerialName("match_about_blank")
        val matchAboutBlank: Boolean? = null,
        @SerialName("match_origin_as_fallback")
        val matchOriginAsFallback: Boolean? = null,
        val world: String? = null,
    )

    @Serializable
    data class ContentSecurityPolicy(
        @SerialName("extension_pages")
        val extensionPages: String?,
        val sandbox: String?,
    )

    @Serializable
    data class CrossOriginEmbedderPolicy(
        val value: String,
    )

    @Serializable
    data class CrossOriginOpenerPolicy(
        val value: String,
    )

    @Serializable
    data class DeclarativeNetRequest(
        @SerialName("rule_resources")
        val ruleResources: List<RuleResource>,
    ) {
        @Serializable
        data class RuleResource(
            val id: String,
            val enabled: Boolean,
            val path: String,
        )
    }

    @Serializable
    data class EventRule(
        val event: String,
        val actions: List<Action>,
        val conditions: List<Condition>,
    ) {
        @Serializable
        data class Action(
            val type: String,
        )

        @Serializable
        data class Condition(
            val type: String,
            val css: String,
        )
    }

    @Serializable
    data class Export(
        val allowlist: List<String>,
    )

    @Serializable
    data class ExternallyConnectable(
        val ids: List<String>,
        val matches: List<String>,
        @SerialName("accepts_tls_channel_id")
        val acceptsTlsChannelId: Boolean,
    )

    @Serializable
    data class FileBrowserHandler(
        val id: String,
        @SerialName("default_title")
        val defaultTitle: String,
        @SerialName("file_filters")
        val fileFilters: List<String>,
    )

    @Serializable
    data class FileSystemProviderCapabilities(
        val source: String,
        val configurable: Boolean? = null,
        val watchable: Boolean? = null,
        @SerialName("multiple_mounts")
        val multipleMounts: Boolean? = null,
    )

    @Serializable
    data class Import(
        val id: String,
        @SerialName("minimum_version")
        val minimumVersion: String? = null,
    )

    @Serializable
    data class InputComponent(
        val name: String,
        val id: String? = null,
        val language: List<String>? = null,
        val layouts: List<String>? = null,
        @SerialName("input_view")
        val inputView: String? = null,
        @SerialName("options_page")
        val optionsPage: String? = null,
    )

    @Serializable
    data class OAuth2(
        @SerialName("client_id")
        val clientId: String,
        val scopes: List<String>,
    )

    @Serializable
    data class Omnibox(
        val keyword: String,
    )

    @Serializable
    data class OptionsUi(
        val page: String,
        @SerialName("open_in_tab")
        val openInTab: Boolean,
    )

    @Serializable
    data class Requirement(
        @SerialName("3D")
        val graphics: Graphics? = null,
        val plugins: Plugins? = null,
    ) {
        @Serializable
        data class Graphics(
            val features: List<String>,
        )

        @Serializable
        data class Plugins(
            val npapi: Boolean,
        )
    }

    @Serializable
    data class Sandbox(
        val pages: List<String>,
    )

    @Serializable
    data class Storage(
        @SerialName("managed_schema")
        val managedSchema: String,
    )

    @Serializable
    data class TtsEngine(
        val voices: Voice,
    ) {
        @Serializable
        data class Voice(
            @SerialName("voice_name")
            val voiceName: String,
            val lang: String,
            @SerialName("event_types")
            val eventTypes: List<String>,
        )
    }

    @Serializable
    data class WebAccessibleResource(
        val resources: List<String>,
        val matches: List<String>,
        @SerialName("extension_ids")
        val extensionIds: List<String>,
        @SerialName("use_dynamic_url")
        val useDynamicUrl: Boolean? = null,
    )
}
