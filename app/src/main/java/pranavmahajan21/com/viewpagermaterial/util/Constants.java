package pranavmahajan21.com.viewpagermaterial.util;


/**
 * Created by pranav on 21/8/16.
 */
public class Constants {
    public static final String APP_NAME = "ZombiesBag";

    /* You can check these mandatory things in EventDAO.java */
    public static final int NO_OF_MANDATORY_THINGS_TO_FIND = 13;
    public static final int DEFAULT_SPLASH_DURATION = 1000;

    public static final String STORAGE_PATH = "https://firebasestorage.googleapis.com";


//    public static final String BUCKET_NAME = "gs://newdbstructure.appspot.com";
//    public static final String USER_ID = "/24NtQyjNCJZ8MLW4OrcQFlchCXZ2";

//    public static final String BUCKET_NAME = "gs://eventsios-3b031.appspot.com";
//public static final String USER_ID = "/UJz8GkXZQZNL5pEISt3Y5rNHmf72";

    public static final String BUCKET_NAME = "gs://eventsweb-pm.appspot.com";
    public static final String USER_ID = "/24NtQyjNCJZ8MLW4OrcQFlchCXZ2";

    public static final String STORAGE_FOLDER_PROFILE = "ProfileImages/";
    public static final String STORAGE_FOLDER_CONTENT = "Contents/";
    public static final String STORAGE_FOLDER_CONTENT_MANDATORY = "ContentsMandatory/";
    public static final String STORAGE_FOLDER_SPONSORS = "Sponsors/";
    public static final String STORAGE_FOLDER_SPEAKER_PROFILE = "SpeakerProfileImages/";

    public static final String PROFILE_FOLDER_ON_DEVICE = "ProfileImages/";

    public static final String NO_PROFILE_IMAGE = "no_profile_image";


    /* Alphabetical */
    public static final String TABLE_EVENT_WEB = "/EventWeb";
    public static final String TABLE_EVENT_APP = "/EventApp";
    public static final String TABLE_EVENT_USER_DATA = "/EventUserData";
    public static final String TABLE_EVENT_ALIAS = "/EventAlias";
    public static final String TABLE_EVENT_LIST = "/EventList";
    public static final String TABLE_EVENT_USER_D_UPDATED_AT = "/userDataUpdatedAt";

    public static final String TABLE_ABOUT = "/About";
    public static final String TABLE_ATTENDEES = "/Invites";
    public static final String TABLE_BASIC_INFO = "/basicInfo";
    public static final String TABLE_CONTENTS = "/Contents";
    public static final String TABLE_CONTENTS_MANDATORY = "/ContentsMandatory";
    public static final String TABLE_CHAT_ROOM_FOR_EVENT = "/ChatRoom";
    public static final String TABLE_CHAT_MESSAGE = "/ChatMessages";
    public static final String TABLE_CONTACT_US = "ContactUs";
    public static final String TABLE_FEEDBACK = "/Feedback";
    public static final String TABLE_MENU_ITEMS = "/MenuItems";
    public static final String TABLE_POLL = "/Poll";
    public static final String TABLE_POLL_ANSWERS = "PollAnswers";
    public static final String TABLE_PROFILE = "/Profile";
    public static final String TABLE_PREFERENCES = "/Preferences";
    public static final String TABLE_SESSION = "/Session";
    public static final String TABLE_SPEAKER = "/Speaker";
    public static final String TABLE_SPONSOR = "/Sponsors";
    public static final String TABLE_SPONSOR_TYPE = "/SponsorsType";
    public static final String TABLE_QUESTIONS = "/Questions";
    public static final String TABLE_QUESTION_ANSWERS = "/QuestionAnswers";
    public static final String TABLE_VERSION = "/version";
//    public static final String TABLE_QUESTIONS_I_LIKE = "/QuestionsILike";
//    public static final String TABLE_QUESTIONS_LIKE_LIST = "/QuestionsLikeList";

    public static final String MENU_ITEM_ABOUT = "About";
    public static final String MENU_ITEM_ATTENDEE = "Attendees";
    public static final String MENU_ITEM_CHATROOM = "Chatroom";
    public static final String MENU_ITEM_CONTACT_US = "Contact Us";
    public static final String MENU_ITEM_CONTENT = "Content";
    public static final String MENU_ITEM_FEEDBACK = "Feedback";
    public static final String MENU_ITEM_POLL = "Polls";
    public static final String MENU_ITEM_SPEAKER = "Speakers";
    public static final String MENU_ITEM_SESSION = "Sessions";
    public static final String MENU_ITEM_SECURITY = "Security";
    public static final String MENU_ITEM_NOTIFICATIONS = "Notifications";
    public static final String MENU_ITEM_SPONSOR = "Sponsors";
    public static final String MENU_ITEM_QNA = "QnA";
    public static final String MENU_ITEM_CUSTOM = "Custom";
    public static final String MENU_ITEM_VENUE_DETAILS = "VenueDetails";
    public static final String MENU_ITEM_INVITATION = "Invitation";
    public static final String MENU_ITEM_ORGANIZER = "jhbj";

    public static final String ABOUT_TYPE_TEXT = "text";
    public static final String ABOUT_TYPE_LIST = "list";
    public static final String ABOUT_TYPE_URL = "url";
    public static final String ABOUT_TYPE_IMAGE = "image";
    public static final String ABOUT_TYPE_IMAGE_SLIDER = "imageSlider";
    public static final String ABOUT_TYPE_YOUTUBE = "youtubeVideo";
    public static final String ABOUT_TYPE6b = "youtubeChannel";
    public static final String ABOUT_TYPE_VIDEO = "video";
    public static final String ABOUT_TYPE_SPEAKERS = "speakers";
    public static final String ABOUT_TYPE_SPONSORS = "sponsors";
    public static final String ABOUT_TYPE_TIME_V = "timeAndLocation";
    public static final String ABOUT_TYPE11_COUNTDOWN = "countdownTimer";
    public static final String ABOUT_TYPE_CARDS = "cards";

    public static final String CONTENT_M_TYPE_ABOUT = "About";
    public static final String CONTENT_M_TYPE_BG = "BGImage";
    public static final String CONTENT_M_TYPE_LOGO = "CustomLogo";
    public static final String CONTENT_M_TYPE_MENU = "Menu";
    public static final String CONTENT_M_TYPE_SESSION = "Session";
    public static final String CONTENT_M_TYPE_SPEAKER = "Speakers";
    public static final String CONTENT_M_TYPE_SPLASH = "Splash";
    public static final String CONTENT_M_TYPE_SPONSORS = "Sponsors";
    public static final String CONTENT_M_TYPE_VENUE = "Venue";

    public static final String PREF_PROFILES = "profiles";
    public static final String PREF_USER_PROFILE = "user_profile";
    public static final String PREF_EVENTS = "all_events";
    public static final String PREF_MY_EVENTS = "all_my_events";
    public static final String PREF_CURRENT_EVENT = "current_event";
    public static final String PREF_PRE_RELEASE_SEARCH = "pre_release_search";
    public static final String PREF_SHOULD_FIND_SPEAKERS_PROFILE = "pref_should_find_speaker_profile";
    public static final String PREF_SHOULD_FIND_ATTENDEES_PROFILE = "pref_should_find_attendee_profile";
    public static final String PREF_SHOULD_FIND_CHATROOM_MESSAGES = "pref_should_find_chatroom_messages";
    public static final String PREF_SESSION_BOOKMARKED = "session_bookmarked";

    public static final String PREF_SPEAKERS = "LovelyCrap";

    /* Receivers */
    public static final String RECEIVER_LOGIN = "loginReceiver";
    public static final String RECEIVER_SIGN_UP = "signUpReceiver";
    public static final String RECEIVER_EVENT = "eventReceiver";
    public static final String RECEIVER_EVENT_ATTENDEES_FOR_LOGIN = "eventInvitesReceiver";
    public static final String RECEIVER_PUBLISH = "publishReceiver";
    public static final String RECEIVER_EVENT_SPEAKERS_FOR_LOGIN = "eventSpeakersReceiver";
    public static final String RECEIVER_EVENT_DATA = "eventDataReceiver";
    public static final String RECEIVER_EVENT_USER_DATA = "eventUserDataReceiver";
    public static final String RECEIVER_EVENT_MENU_ACTIVITY = "updateEventReceiver";
    public static final String RECEIVER_LATEST_VERSION_CHECK = "latestVersionCheckReceiver";
    public static final String RECEIVER_ATTENDEES_PROFILE = "attendeesProfileReceiver";
    public static final String RECEIVER_SPEAKERS_PROFILE = "speakerssProfileReceiver";
    public static final String RECEIVER_CONTENT_MANDATORY_DOWNLOADED_FILES = "reijvn";
    public static final String RECEIVER_USER_D_UPDATED_AT = "YTRvn";


    public static final String RECEIVER_BASIC_INFO_LIST = "basicInfoListReceiver";
    public static final String RECEIVER_FIND_EVENT = "findEventReceiver";               // Used in UnusedEventLoginActivity
    public static final String RECEIVER_PRE_RELEASE = "preReleaseReceiver";               // Used in UnusedEventLoginActivity
    public static final String RECEIVER_MY_EVENT_ACTION = "myEventActionReceiver";               // Used in UnusedEventLoginActivity
    public static final String RECEIVER_REMOVE_EVENT_FROM_LIST = "removeEventFromListReceiver";               // Used in UnusedEventLoginActivity
    public static final String RECEIVER_CONTENT_DATA = "contentDataReceiver";               // Used in UselessSessionDetailsActivity & ContentELVActivity
    public static final String RECEIVER_CONTENT_LIST = "contentListReceiver";
    public static final String RECEIVER_CONTENT_UPLOAD = "contentUploadReceiver";
    public static final String RECEIVER_SESSION_LIST = "sessionListReceiver";
    public static final String RECEIVER_SPEAKER_LIST = "speakerListReceiver";
    public static final String RECEIVER_SPONSOR_LIST = "sponsorListReceiver";
    public static final String RECEIVER_ATTENDEE_LIST = "attendeeListReceiver";
    public static final String RECEIVER_POLL_LIST = "pollListReceiver";
    public static final String RECEIVER_QUESTION_LIST = "questionListReceiver";
    public static final String RECEIVER_CHATROOM_MESSAGE_LIST = "chatroomMessageListReceiver";
    public static final String RECEIVER_CHATFRIEND_LIST_NEW_MESSAGE = "chatFriendListNewMessageReceiver";
    public static final String RECEIVER_CHAT121_NEW_MESSAGE = "chat121NewMessageReceiver";

    public static final String RECEIVER_SPEAKER_PROFILE_IMAGE_LISTACTIVITY = "FHFGBHReceiver";
    public static final String RECEIVER_SPEAKER_PROFILE_IMAGE_DETAILSACTIVITY = "FHFGBsdfsdHReceiver";

    public static final String RECEIVER_ATTENDEE_PROFILE_IMAGE_LISTACTIVITY = "FHSDFBHReceiver";
    public static final String RECEIVER_ATTENDEE_PROFILE_IMAGE_DETAILSACTIVITY = "FHFdthdHReceiver";


    public static final String RECEIVER_QUESTION_LIKE = "questionLikeReceiver";
    public static final String RECEIVER_QUESTION_ASK = "questionAskReceiver";
    public static final String RECEIVER_QUESTION_ANSWER = "questionAnswerReceiver";
    public static final String RECEIVER_POLL_ANSWER = "pollAnswerReceiver";
    /* Receivers */


    public static final String EXTRAS_IS_LOGIN_SUCCESSFUL = "is_login_successful";
    public static final String EXTRAS_LOGIN_FAIL_ERROR_MSG = "login_fail_error_msg";
    public static final String EXTRAS_IS_LOGIN_USER_PROFILE_FOUND = "is_login_user_profile_found";
    public static final String SHOULD_COMPLETE_PROFILE = "TYJ";
    public static final String EXTRAS_SHOULD_LOGOUT = "ksdbvch";

    public static final String EXTRAS_IS_SIGN_UP_SUCCESSFUL = "is_sign_up_successful";
    public static final String EXTRAS_SIGN_UP_USERNAME = "sign_up_username";
    public static final String MY_EVENT_ACTION = "action";               // Used in UnusedEventLoginActivity
    public static final String MY_EVENT_ACTION_REVIEW = "actionReview";               // Used in UnusedEventLoginActivity
    public static final String MY_EVENT_ACTION_PUBLISH = "actionPublish";               // Used in UnusedEventLoginActivity
    public static final String EXTRAS_SIGN_UP_ERROR_MSG = "sign_up_error_msg";
    public static final String EXTRAS_SWITCH_EVENT = "switch_event";
    public static final String EXTRAS_IS_EVENT_FOUND = "dd";
    public static final String EXTRAS_IS_EVENT_BEING_UPDATED = "nj";
    public static final String EXTRAS_IS_PRE_RELEASE_SEARCH = "njEDTGV";
    public static final String EXTRAS_EVENT_INVITES = "ee";
    public static final String EXTRAS_IS_NEWLY_FOUND_EVENT = "ISnEWLYfOUNDeVENT";
    public static final String EXTRAS_LATEST_EVENT = "gg";
    public static final String EXTRAS_EDIT_PROFILE = "gbfxg";
    public static final String EXTRAS_COMPLETE_PROFILE = "gbrng";
    public static final String EXTRAS_LATEST_VERSION = "tfhgh";
    public static final String EXTRAS_USER_D_UPDATED_AT = "nbvweh";

    public static final String EXTRAS_EVENT_DATA_RESPONSE = "eventDataResponse";
    public static final String EXTRAS_POLL_RESPONSE = "pollResponse";
    public static final String EXTRAS_ABOUT_RESPONSE = "aboutResponse";
    public static final String EXTRAS_SESSION_RESPONSE = "sessionResponse";
    public static final String EXTRAS_CONTENT_RESPONSE = "contentResponse";
    public static final String EXTRAS_CONTACT_US_RESPONSE = "contactUsResponse";
    public static final String EXTRAS_CONTENT_MANDATORY_RESPONSE = "contentMandatoryResponse";
    public static final String EXTRAS_CONTENT_UPLOADED = "contentUploaded";
    public static final String EXTRAS_SPEAKER_RESPONSE = "speakerResponse";
    public static final String EXTRAS_SPONSOR_RESPONSE = "sponsorResponse";
    public static final String EXTRAS_QUESTION_RESPONSE = "questionResponse";
    public static final String EXTRAS_ATTENDEE_RESPONSE = "attendeeResponse";
    public static final String EXTRAS_BASIC_INFO_LIST = "oeirngv";
    public static final String EXTRAS_EVENT_ID = "eventId";
    public static final String EXTRAS_EVENT_ALIAS_ID = "eventAliasId";

    public static final String EXTRAS_SESSION_INDEX = "sessionIndex";
    public static final String EXTRAS_SESSION_ID = "sessionId";
    public static final String EXTRAS_SESSION_SPEAKER_LIST = "sessionSpeakerList";
    public static final String EXTRAS_SESSION_QUESTION_LIST = "sessionQuestionList";
    public static final String EXTRAS_SESSION_CONTENT_LIST = "sessionContentList";
    public static final String EXTRAS_SESSION_POLL_LIST = "sessionPollList";
    public static final String EXTRAS_IS_SPEAKER = "isSpeaker";
    public static final String EXTRAS_SPEAKER = "speakerIndex";
    public static final String EXTRAS_SPONSOR_INDEX = "sponsorIndex";
    public static final String EXTRAS_SPONSOR_ID = "sponsorId";
    public static final String EXTRAS_QUESTION = "question";
    public static final String EXTRAS_QUESTION_ID = "questionId";
    public static final String EXTRAS_QUESTION_INDEX = "questionIndex";
    public static final String EXTRAS_ATTENDEE_INDEX = "attendeeIndex";
    public static final String EXTRAS_ATTENDEE_EMAIL = "attendeeEmail";
    public static final String EXTRAS_ATTENDEE_PROFILE = "attendeeEmailSD";
    public static final String EXTRAS_IS_PROFILE_IMAGE_FOUND = "isProfileImageFound";
    public static final String EXTRAS_SESSION_DAY_LIST = "sessionDayList";
    public static final String EXTRAS_DAY_NO = "dayNo";
    public static final String EXTRAS_QCP_TYPE = "qcpType";
    public static final String QCP_TYPE_Q = "Q";
    public static final String QCP_TYPE_P = "P";
    public static final String QCP_TYPE_C = "C";

    public static final String LOGOUT_TITLE = "Are you sure you want to logout?";
    public static final String LOGOUT_MESSAGE = "You will lose all your saved data. This includes, all your recently searched events & all the downloaded content.";


    public static final String EXTRAS_CHATEE_EMAIL = "chateeEmail";
    public static final String EXTRAS_CHATEE_INDEX = "chateeIndex";
    public static final String EXTRAS_CHAT_MSG = "chatMsgObj";

    public static final String EXTRAS_PROFILE = "zz";
    public static final String EXTRAS_IS_PROFILE_GENERIC = "zz";

    /* This is used to avoid infinite loop between sessions & speakers */
    public static final String EXTRAS_RESTRICT_FURTHER_FLOW = "restrict";
    public static final String EXTRAS_YOUTUBE_ID = "youtubeID";

    public static final String EXTRAS_QUESTION_UPDATED = "questionUpdated";

    public static final int ATTENDING = 0;
    public static final int ATTENDING_NOT = 1;
    public static final int ATTENDING_MAYBE = 2;
    public static final int NOT_ANSWERED_AS_YET = 3;

    public static final String ATTENDING_STRING = "Attending";
    public static final String ATTENDING_NOT_STRING = "Not Attending";
    public static final String ATTENDING_MAYBE_STRING = "Maybe";
    public static final String NOT_ANSWERED_AS_YET_STRING = "Not Answered";

    public static final int EVENT_NOT_IN_LIST = -1;


    public static final int MENU_CONTEXT_ITEM_10_ID = 10;
    public static final String MENU_CONTEXT_ITEM_10_TITLE = "Remove Event from list";

    public static final String MESSAGE = "This is a custom profile for only this event.";

    public static final int REQ_CODE_COMPLETE_PROFILE = 20;

    public static final int REQ_CODE_QUESTION_ASK = 60;
    public static final int REQ_CODE_QUESTION_REPLY = 61;

    public static final String EVENT_PREF_MAP_MENU = "menu";
    public static final String EVENT_PREF_MAP_VENUE = "venue";
    public static final String EVENT_PREF_MAP_SPLASH = "splash_map";
    public static final String EVENT_PREF_MAP_SPONSORS = "sponsors";
    public static final String EVENT_PREF_MAP_CUSTOM_LOGO = "custom_logo";

    public static final String EVENT_PREF_HAS_CUSTOM_LOGO = "has_custom_logo";
    public static final String EVENT_PREF_HAS_CUSTOM_LOGO_ON_EVERY_PAGE = "has_custom_logo_on_every_page";
    public static final String EVENT_PREF_CUSTOM_LOGO_ID = "custom_logo_id";
    public static final String EVENT_PREF_CUSTOM_LOGO_HAS_TINT = "custom_logo_has_tint";
    public static final String EVENT_PREF_CUSTOM_LOGO_TINT_COLOR = "custom_logo_tint_color";

    public static final String EVENT_PREF_MENU_TYPE = "menu_type";
    public static final String EVENT_PREF_MENU_HAS_HEADER = "menu_has_header";
    public static final String EVENT_PREF_MENU_HEADER_TYPE = "menu_header_type";
    public static final String EVENT_PREF_MENU_CONTENT_MANDATORY_LIST = "menu_content_mandatory_list";
    public static final String EVENT_PREF_MENU_YOUTUBE_URL = "menu_youtube_url";

    public static final String EVENT_PREF_MENU_HAS_CUSTOM_BG = "menu_has_custom_bg";
    public static final String EVENT_PREF_MENU_BG_TYPE = "menu_bg_type";
    public static final String EVENT_PREF_MENU_BG_COLOR = "menu_bg_color";
    public static final String EVENT_PREF_MENU_BG_IMAGE_TYPE = "menu_bg_image_type";
    public static final String EVENT_PREF_MENU_BG_IMAGE_ID = "menu_bg_image_id";
    public static final String EVENT_PREF_MENU_BG_IMAGE_HAS_BLUR = "menu_bg_image_has_blur";
    public static final String EVENT_PREF_MENU_BG_IMAGE_BLUR_INDEX = "menu_bg_image_blur_index";

    public static final String EVENT_PREF_MENU_ICON_REMOVE_CARD = "menu_icon_remove_card";
    public static final String EVENT_PREF_MENU_ICON_CARD_COLOR = "menu_icon_card_color";
    public static final String EVENT_PREF_MENU_ICON_COLOR = "menu_icon_color";
    public static final String EVENT_PREF_MENU_ICON_TEXT_COLOR = "menu_icon_text_color";

    public static final String EVENT_PREF_HAS_CUSTOM_CARD_COLOR = "has_custom_card_color";
    public static final String EVENT_PREF_HAS_CUSTOM_ICON_COLOR = "has_custom_icon_color";
    public static final String EVENT_PREF_HAS_CUSTOM_ICON_TEXT_COLOR = "has_custom_icon_text_color";


    public static final String EVENT_PREF_THEME = "theme_type";
    public static final String EVENT_PREF_TOOLBAR_COLOR = "toolbarColor";
    public static final String EVENT_PREF_SPEAKERS_PRIORITY_ORDER = "speakers_priority_order";

    public static final String EVENT_PREF_HAS_VENUE_MAP = "has_venue_map";
    public static final String EVENT_PREF_VENUE_MAP_ID = "venue_map_id";
    public static final String EVENT_PREF_TIMINGS = "timings";

    public static final String EVENT_PREF_HAS_SPLASH_SCREEN = "has_splash_screen";
    public static final String EVENT_PREF_SPLASH_TYPE = "splash_screen_type";
    public static final String EVENT_PREF_SPLASH_DURATION = "splash_duration";
    public static final String EVENT_PREF_SPLASH_REMOVE_APP_BRANDING = "splash_remove_app_branding";

    public static final String EVENT_PREF_SPLASH_HAS_HEADER = "splash_has_header";
    public static final String EVENT_PREF_SPLASH_HEADER_TEXT_TOP = "splash_header_text_top";
    public static final String EVENT_PREF_SPLASH_HEADER_TEXT_BOTTOM = "splash_header_text_bottom";
    public static final String EVENT_PREF_SPLASH_HEADER_IMAGE_CM_ID = "splash_header_image_cm_id";
    public static final String EVENT_PREF_SPLASH_HAS_HEADER_TEXT_TOP = "splash_has_header_text_top";
    public static final String EVENT_PREF_SPLASH_HAS_HEADER_TEXT_BOTTOM = "splash_has_header_text_bottom";

    public static final String EVENT_PREF_SPLASH_HAS_FOOTER = "splash_has_footer";
    public static final String EVENT_PREF_SPLASH_FOOTER_TEXT_TOP = "splash_footer_text_top";
    public static final String EVENT_PREF_SPLASH_FOOTER_TEXT_BOTTOM = "splash_footer_text_bottom";
    public static final String EVENT_PREF_SPLASH_FOOTER_IMAGE_CM_ID = "splash_footer_image_cm_id";
    public static final String EVENT_PREF_SPLASH_HAS_FOOTER_TEXT_TOP = "splash_has_footer_text_top";
    public static final String EVENT_PREF_SPLASH_HAS_FOOTER_TEXT_BOTTOM = "splash_has_footer_text_bottom";

    public static final String EVENT_PREF_SPLASH_HAS_CUSTOM_BG_COLOR = "splash_has_custom_bg_color";
    //    public static final String EVENT_PREF_SPLASH_HAS_CUSTOM_TEXT_COLOR = "splash_has_custom_text_color";
    // TODO: 3/28/18
    public static final String EVENT_PREF_SPLASH_CUSTOM_TEXT_COLOR = "splash_custom_text_color";
    public static final String EVENT_PREF_SPLASH_CUSTOM_BG_COLOR = "splash_custom_bg_color";

    public static final String EVENT_PREF_SPONSORS_HAS_EXPANDED_VIEW = "sponsors_has_expanded_view";
    //    public static final String EVENT_PREF_SPONSORS_HAS_CUSTOM_PRIORITY_ORDER = "sponsors_has_custom_priority_order";
//    public static final String EVENT_PREF_SPONSORS_PRIORITY_ORDER = "sponsors_priority_order";

    public static final String EVENT_PREF_MENU_BG_IMAGE_TYPE_FULLSCREEN = "fullscreen";
    public static final String EVENT_PREF_MENU_BG_IMAGE_TYPE_PARTIAL = "partial";

    public static final String EVENTS_RECENTLY_SEARCHED = "Recently Searched Events";
    public static final String EVENTS_CREATED_BY_ME = "Events Created By Me";

    public static final String QUERY_RESULT= "mbhwegh";

//    public static final String DEFAULT_MENU_ICON_COLOR = "#AAAAAA";
//    public static final String DEFAULT_MENU_ICON_TEXT_COLOR = "#AAAAAA";
//    public static final String DEFAULT_MENU_ICON_CARD_COLOR = "#AAAAAA";

    public static final String DEFAULT_SPLASH_BG_COLOR = "#FFFFFF";

    public enum MenuIconColors {
        /* https://www.mkyong.com/java/java-enum-example/
         * Use the 2nd or 3rd example to get color code
         * https://stackoverflow.com/a/604426/2937847
         * http://cloford.com/resources/colours/500col.htm
         * */

        /* If we maintain all these colors in colors.xml then we'll be able to see the colors as well */
        WHITE("#FFFFFF"),
        BLACK("#000000"),

//        RED_LIGHT("#"),

        RED_LIGHT("#DC143C"),
        RED_DARK("#B0171F"),

        GREEN_LIGHT("#"),
        GREEN_DARK("#"),

        BLUE1("#1E90FF"),
        BLUE2("#6495ED"),
        BLUE3("#4169E1"),
        BLUE4("#00008B"),
        BLUE5("#63B8FF"),
        BLUE6("#00BFFF"),
        BLUE7("#33A1C9"),

        PINK_LIGHT("#"),
        PINK_DARK("#"),

        BROWN("#"),
        ORANGE("#"),

        PURPLE("#"),
        YELLOW("#"),
        GOLD("#"),
        GRAY_DARK("#AAAAAA"),
        GRAY_LIGHT("#"),

        DEFAULT("#AAAAAA");
        private String colorCode;

        MenuIconColors(String colorCode) {

            this.colorCode = colorCode;
        }

        public String getColorCode() {
            return colorCode;
        }

    }

    public enum SplashTextColors {
        /* If we maintain all these colors in colors.xml then we'll be able to see the colors as well */
        WHITE("#FFFFFF"),
        BLACK("#000000"),

        RED_LIGHT("#DC143C"),
        RED_DARK("#B0171F"),

        GREEN_LIGHT("#"),
        GREEN_DARK("#"),

        BLUE1("#1E90FF"),
        BLUE2("#6495ED"),

        PINK_LIGHT("#"),
        PINK_DARK("#"),

        BROWN("#"),
        ORANGE("#"),

        PURPLE("#"),
        YELLOW("#"),
        GOLD("#"),
        GRAY_DARK("#AAAAAA"),
        GRAY_LIGHT("#"),

        DEFAULT("#AAAAAA");
        private String colorCode;

        SplashTextColors(String colorCode) {
            this.colorCode = colorCode;
        }

        public String getColorCode() {
            return colorCode;
        }

    }



    public static final String ROLE_OWNER = "owner";
    public static final String ROLE_SPEAKER = "speaker";
    public static final String ROLE_NEITHER_OWNER_NOR_SPEAKER = "neither_owner_nor_speaker";
    public static final String ROLE_ATTENDEE = "attendee";

    public enum Roles {
        /* If we maintain all these colors in colors.xml then we'll be able to see the colors as well */
        OWNER,
        SPEAKER,
        ATTENDEE;


//        OWNER("#FFFFFF"),
//        SPEAKER("#000000"),
//        ATTENDEE("#DC143C");
//
////        DEFAULT("#AAAAAA");
//        private String colorCode;
//
//        Roles(String colorCode) {
//            this.colorCode = colorCode;
//        }
//
//        public String getColorCode() {
//            return colorCode;
//        }

    }


    public static final String POLL_TYPE_SCQ = "scq";
    public static final String POLL_TYPE_MCQ = "mcq";
    public static final String POLL_TYPE_RATING = "rating";

    public static final String SCREEN_MENU = "menu";
    public static final String SCREEN_SESSION_DETAIL = "session";


    public static final String SPONSOR_TYPE1 = "Sponsor";
    public static final String SPONSOR_TYPE2 = "Co-Sponsor";

    public static final String DOCUMENTATION = "swkjehrweskjfh";

    // TODO: 9/4/17 Remove API key from here. Keep it secured
    public static final String ANDROID_API_KEY = "AIzaSyCYL1GhA1nOE1T5KIDpymCH2okPeT0ZvE0";

    public static final String TEXT1 = "Update notes by the organizer:";
    public static final String TEXT2 = "This event requires a mandatory update";
    public static final String TEXT3 = "An update is available for this event";
    public static final String TEXT4 = "The event is currently disabled by the organizer";
    public static final String TEXT5 = "The event is currently disabled by the app Admin";
    public static final String TEXT6 = "This may take a while";

    public static final String TEXT_PRE_RELEASE_TEST_MESSAGE = "You are testing the Event in Pre-Release Mode";

}
