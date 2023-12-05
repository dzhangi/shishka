package com.octocavern.data.model

import com.google.gson.annotations.SerializedName

data class ProjectDto(
    @SerializedName("anon_permissions")
    val anonPermissions: List<Any>,
    @SerializedName("blocked_code")
    val blockedCode: Any,
    @SerializedName("created_date")
    val createdDate: String,
    @SerializedName("creation_template")
    val creationTemplate: Int,
    @SerializedName("default_epic_status")
    val defaultEpicStatus: Int,
    @SerializedName("default_issue_status")
    val defaultIssueStatus: Int,
    @SerializedName("default_issue_type")
    val defaultIssueType: Int,
    @SerializedName("default_points")
    val defaultPoints: Int,
    @SerializedName("default_priority")
    val defaultPriority: Int,
    @SerializedName("default_severity")
    val defaultSeverity: Int,
    @SerializedName("default_swimlane")
    val defaultSwimlane: Any,
    @SerializedName("default_task_status")
    val defaultTaskStatus: Int,
    @SerializedName("default_us_status")
    val defaultUsStatus: Int,
    @SerializedName("description")
    val description: String,
    @SerializedName("i_am_admin")
    val iAmAdmin: Boolean,
    @SerializedName("i_am_member")
    val iAmMember: Boolean,
    @SerializedName("i_am_owner")
    val iAmOwner: Boolean,
    @SerializedName("id")
    val id: Int,
    @SerializedName("is_backlog_activated")
    val isBacklogActivated: Boolean,
    @SerializedName("is_contact_activated")
    val isContactActivated: Boolean,
    @SerializedName("is_epics_activated")
    val isEpicsActivated: Boolean,
    @SerializedName("is_fan")
    val isFan: Boolean,
    @SerializedName("is_featured")
    val isFeatured: Boolean,
    @SerializedName("is_issues_activated")
    val isIssuesActivated: Boolean,
    @SerializedName("is_kanban_activated")
    val isKanbanActivated: Boolean,
    @SerializedName("is_looking_for_people")
    val isLookingForPeople: Boolean,
    @SerializedName("is_private")
    val isPrivate: Boolean,
    @SerializedName("is_watcher")
    val isWatcher: Boolean,
    @SerializedName("is_wiki_activated")
    val isWikiActivated: Boolean,
    @SerializedName("logo_big_url")
    val logoBigUrl: Any,
    @SerializedName("logo_small_url")
    val logoSmallUrl: Any,
    @SerializedName("looking_for_people_note")
    val lookingForPeopleNote: String,
    @SerializedName("members")
    val members: List<Int>,
    @SerializedName("modified_date")
    val modifiedDate: String,
    @SerializedName("my_homepage")
    val myHomepage: Int,
    @SerializedName("my_permissions")
    val myPermissions: List<String>,
    @SerializedName("name")
    val name: String,
    @SerializedName("notify_level")
    val notifyLevel: Int,
    @SerializedName("owner")
    val owner: Owner,
    @SerializedName("public_permissions")
    val publicPermissions: List<Any>,
    @SerializedName("slug")
    val slug: String,
    @SerializedName("tags")
    val tags: List<Any>,
    @SerializedName("tags_colors")
    val tagsColors: TagsColors,
    @SerializedName("total_activity")
    val totalActivity: Int,
    @SerializedName("total_activity_last_month")
    val totalActivityLastMonth: Int,
    @SerializedName("total_activity_last_week")
    val totalActivityLastWeek: Int,
    @SerializedName("total_activity_last_year")
    val totalActivityLastYear: Int,
    @SerializedName("total_closed_milestones")
    val totalClosedMilestones: Int,
    @SerializedName("total_fans")
    val totalFans: Int,
    @SerializedName("total_fans_last_month")
    val totalFansLastMonth: Int,
    @SerializedName("total_fans_last_week")
    val totalFansLastWeek: Int,
    @SerializedName("total_fans_last_year")
    val totalFansLastYear: Int,
    @SerializedName("total_milestones")
    val totalMilestones: Any,
    @SerializedName("total_story_points")
    val totalStoryPoints: Any,
    @SerializedName("total_watchers")
    val totalWatchers: Int,
    @SerializedName("totals_updated_datetime")
    val totalsUpdatedDatetime: String,
    @SerializedName("videoconferences")
    val videoconferences: Any,
    @SerializedName("videoconferences_extra_data")
    val videoconferencesExtraData: Any
)