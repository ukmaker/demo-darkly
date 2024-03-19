# Demo Darkly - A Demo App For Launch Darkly

[Launch Darkly](https://launchdarkly.com/) provides a set of cloud services and programming language integrations to make it
easy to manage new features, targetted content, experiments and more.

Website or app owners no longer need to write the code to run A/B tests, do market segmentation and log the results. Launch Darkly does it all for you!

This project is a small test written in Java using the Spring framework. It exercises the following two features of Launch Darkly
 - [Kill Switches](https://docs.launchdarkly.com/home/flag-templates/kill-switch-flags)
 - [Percentage Rollouts](https://docs.launchdarkly.com/home/targeting-flags/rollouts)

## Demo Website Structure
The demo website has three pages, two of which come in a couple of variants
- index page links to /greetings and /partings
  - /greetings has two variants, controlled by a LaunchDarkly Flag called "flag1".
  - /partings also has two variants, controlled by "flag2"
 
## Flags
### Flag1
This is setup as a PercentageRollout of two options, split by user key.
Note that the Spring code for the website generates a random user key for each request, so the
page which ends up being served will be chosen randomly between the two options.

## Flag2
This flag is setup as a KillSwitch, so the page which ends up being served depends on 
whether the flag is active in your LaunchDarkly console.

## Setup
First, you will need to head over to [LaunchDarkly](https://launchdarkly.com/) and set up a trial account.

Next, set up the two flag definitions.

For the PercentageRollout flag:
- In your LaunchDarkly console, navigate to Feature Flags and click on the CreateFlag button.
- In the Details box enter "Flag1" for the Name and "flag1" for the key
- Select the Custom Configuration, for a Boolean flag type
- There will be two variations, name one "enabled" with value "true", and the other "disabled" with value false
- Click Create flag. This should take you to the rules page where you define how to choose between the enabled and disabled state.
- Click on the Edit button for the Default rule and select "a percentage rollout" from the Serve dropdown
- Choose the percentage split you want to use - I just entered 50% for enabled
- Context kind should be "user" and the Attribute should be "key"
- Click Review and Save. Type "production" into the Confirm box so you can Save changes

Repeat the setup for the KillSwitch flag:
- Navigate to Feature Flags and click on the CreateFlag button.
- Name this flag "Flag2" with key "flag2"
- Select the Kill switch configuration and leave the defaults alone
- Click Create flag

## Get ready to run the code
- Clone this repository to a machine with internet access and on which you have installed Java17.
- In the directory `src/main/resources` you will find the file `application.properties`. Edit this file
and enter your sdkKey [see here for instructions on retrieving the key through the search box](https://docs.launchdarkly.com/home/getting-started#getting-started-as-a-software-developer).
- If you've used different names for the flags, change the properties for `greetingsKey` and `partingsKey` to match.
- Now you should be able to build the code by running `./mvnw package`
- And finally run the code using `java -jar ./target/launch-darkly-0.0.1-SNAPSHOT.jar`

Now you can access the development server through your browser by navigating to `http://localhost:8080`.

When you go to the `/greetings` page you should see one or other of the two messages randomly: "Hello.." or "Greetings..".
The split between the pages depends on the percentage you entered when you set up Flag1 above.

As for the `/partings` page, this is controlled by turning on or off the KillSwitch in the LaunchDarkly console.

 - With the switch on, the page says "So Long And Thanks For All The Fish".
 - With it off it says "On va se revoir, le jour viendra!"

