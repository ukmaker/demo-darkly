package com.demo.darkly;

import com.launchdarkly.sdk.LDContext;
import com.launchdarkly.sdk.server.LDClient;
import com.launchdarkly.sdk.server.LDConfig;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:xapplication.properties")
public class LDBean {
    // Set SDK_KEY to your LaunchDarkly SDK key.
    @Value("${sdkKey}")
    public String SDK_KEY;

    // Set FEATURE_FLAG_KEY to the feature flag key you want to evaluate.
    public String HOME_PROMOTION_KEY = "homePromotion";
    public String HOME_MENU_VARIANT_KEY = "homeMenuVariant";

    public String GREETINGS_KEY = "flag1";

    public String PARTINGS_KEY = "flag3";

    public String ACCOUNT_FLAG_KEY = "accountFlag";

    protected LDConfig config;
    protected LDClient client;

    protected LDContext context;

    public LDBean() {
    }

    public LDClient getClient() {

        if(client == null) {
            config = new LDConfig.Builder().build();
            client = new LDClient(SDK_KEY, config);

            if (client.isInitialized()) {
                showMessage("SDK successfully initialized!");
            } else {
                showMessage("SDK failed to initialize");
                System.exit(1);
            }
        }

        return client;
    }
    private static void showMessage(String s) {
        System.out.println("*** " + s);
        System.out.println();
    }

    public LDContext getContext() {
        String key = RandomStringUtils.randomAlphanumeric(10);
        context = LDContext.builder("demo-key-" +key)
                .name("Zaphod")
                .build();
        return context;
    }

    public boolean homePagePromotion() {
        return getClient().boolVariation(HOME_PROMOTION_KEY, getContext(), false);
    }

    public boolean homeMenuVariant() {
        return getClient().boolVariation(HOME_MENU_VARIANT_KEY, getContext(), false);
    }
    public boolean greetingVariant() {
        return getClient().boolVariation(GREETINGS_KEY, getContext(), false);
    }
    public boolean partingVariant() {
        return getClient().boolVariation(PARTINGS_KEY, getContext(), false);
    }

    public boolean accountFlag() {
        return getClient().boolVariation(ACCOUNT_FLAG_KEY, getContext(), false);
    }

}
