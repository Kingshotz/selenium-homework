package ge.tbc.testautomation.data;

public class Constants {
    public static final String
            URL_DYNAMIC_CONTROLS = "http://the-internet.herokuapp.com/dynamic_controls",
            HEADER_TEXT = "Dynamic Controls",
            DESCRIPTION_TEXT = "This example demonstrates when elements (e.g., checkbox, input field, etc.) are changed asynchronously.",
            URL_DRAG_AND_DROP = "http://the-internet.herokuapp.com/drag_and_drop",
            ELEMENTAL_SELENIUM_HREF = "http://elementalselenium.com/",
            URL_ULTIMATE_QA_AUTOMATION = "https://ultimateqa.com/automation",
            URL_ULTIMATE_QA_CONSULTING = "https://ultimateqa.com/consulting/",
            URL_JQUERY_SLIDER = "https://jqueryui.com/slider/",
            EFFECTS_XPATH = "//h3[text() = 'Effects']/following-sibling::ul/li",
            URL_INTERNET_HOOKUP_ADD_OR_REMOVE =  "http://the-internet.herokuapp.com/add_remove_elements/",
            ADD_ELEMENT_XPATH = "//button[text() = 'Add Element']",
            LAST_DELETE_BUTTON_SELECTOR = "#elements button:last-of-type",
            DELETE_BUTTONS_SELECTOR = "button[class ^='added']",
            URL_CHALLENGING_DOM = "http://the-internet.herokuapp.com/challenging_dom",
            LOREM_BY_IPSUM_VALUE_XPATH = "//td[text()='Apeirian9']/preceding-sibling::td[1]",
            ADDED_MANUALLY_CLASS = "added-manually",
            DELETE_ELEMENT_ONCLICK = "deleteElement()",
            OPTION_XPATH_TEMPLATE = "//*[self::li or self::div or self::span or self::a or self::option]" +
                            "[normalize-space(text())='%s']",
            URL_TYMPANUS_CUSTOM_DROP_DOWN = "https://tympanus.net/Tutorials/CustomDropDownListStyling/",
            XPATH_DEMO_1 = "//a[text() ='Demo 2']",
            URL_TECHCANVAS_REGISTER ="https://techcanvass.com/examples/register.html",
            XPATH_RADIO_MALE = "//input[@value='male']",
            XPATH_MODEL_SELECTOR ="//select[@name='model']",
            XPATH_MODEL_OPTION_TEXT = "Mega 123 Medium screen",
            XPATH_GENERIC_INPUT = "//input[@required and @value='%s']",
            XPATH_REGISTER_BUTTON = "//input[@value='Register']",
            ALERT_TEXT = "Thanks for registering, we will get back to you shortly",
            CSS_SELECTOR_DISABLE_ENABLE_BUTTON = "form#input-example button[onclick*='swapInput']",
            CSS_SELECTOR_PROGRESS_BAR = "#loading",
            CSS_SELECTOR_TEXT_INPUT = "form#input-example input[type='text']",
            URL_DEMOQA_PROGRESS_BAR = "https://demoqa.com/progress-bar",
            CSS_SELECTOR_DEEMOQA_RESET_BUTTON = "#resetButton",
            CSS_SELECTOR_DEEMOQA_START_BUTTON = "#startStopButton",

            CSS_SELECTOR_DEEMOQA_PROGRESS_BAR ="#progressBar>div[role= 'progressbar']",
            URL_SCROLL_TEST = " https://www.techlistic.com/2017/02/automate-demo-web-table-with-selenium.html",
                    XPATH_ALL_CODE_BLOCKS =
            "//div[contains(@style,'solid gray')][preceding::h3[contains(., '%s')] and following::h3[contains(., '%s')]] |" +
                    " //div[contains(@class,'bg-black') and contains(@class,'rounded-md')][preceding::h3[contains(., '%s')] and following::h3[contains(., '%s')]]",
            XPATH_CODE_BLOCK_HEADER = "//h3[contains(normalize-space(.), '%s')]",
                    CSS_POPULAR_POST_TITLE_LINK = "h3.post-title > a",
            XPATH_LAST_ELEMENT = "//h2[text() = 'Privacy Preferences']";
    //write here max+1
    public static final int MAX_PARAGRAPH_NUM = 5,
            Max_HEADER_NUM = 6,
            CODE_BLOCKS_NUM = 7,
            POPULAR_POSTS_NUM = 9;
}
