
使用方法

@Configuration
public class I18nConfiguration {


    @Autowired
    private MessageSource messageSource;

    @PostConstruct
    private void init() {
        //final MessageSource messageSource = resourceBundleMessageSource();
        I18nMessageHolder.register(key -> messageSource.getMessage(key, null, I18nContext.getUserLocale()));
    }


    private MessageSource resourceBundleMessageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasenames("mess");
        return messageSource;
    }

}