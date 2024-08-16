# PluginAnalyse

![License](https://img.shields.io/badge/license-MIT-green.svg) ![Version](https://img.shields.io/badge/version-1.3-blue.svg)

**PluginAnalyse** is a Minecraft Bukkit/Spigot plugin designed to monitor and analyze network traffic in real-time. It provides server administrators with powerful tools to view, clear, and check connections, particularly for identifying connections related to specific domains or IPs, such as "ethanol.rocks". The plugin also includes permissions-based controls to ensure only authorized users can perform certain actions.

## Features

- **Log Analysis:** Easily view and manage logs of network packets related to player connections.
- **Ethanol Connection Detection:** Automatically check logs for connections to "ethanol.rocks" or a specified IP address and alert administrators if found.
- **Discord Alerts:** Integrate with Discord via a webhook to send alerts whenever an Ethanol connection is detected and blocked.
- **Permission-Based Access:** Restrict access to specific commands based on user permissions.
- **Centralized Messaging:** Utilize a simple utility class for consistent and formatted messaging across the plugin.

## Download

### Stable Release
[GitHub](https://github.com/PublicCode1337/PluginAnalyse/releases/tag/Minecraft)
### Beta Release
[GitHub](https://github.com/PublicCode1337/PluginAnalyse/releases/tag/Beta) 

## Commands

### `/analyse <view|clear|checkethanol>`
The main command for interacting with the plugin. Subcommands include:

- **view**: View the logged network connections.
- **clear**: Clear the log file.
- **checkethanol**: Check the logs for connections to "ethanol.rocks" or the specified IP.

### Command Usage
- `/analyse view`: Displays the contents of the log file to the user.
- `/analyse clear`: Clears the log file.
- `/analyse checkethanol`: Checks the log file for any connections to "ethanol.rocks" or the specified IP and reports the findings.

### Permissions
- **analyse.viewlogs**: Allows the user to view the logs.
- **analyse.clearlogs**: Allows the user to clear the logs.
- **analyse.checkethanol**: Allows the user to check the logs for Ethanol-related connections.

## Installation

1. Download the latest release from the [Releases](#) page.
2. Place the `PluginAnalyse.jar` file in your server's `plugins` directory.
3. Start or restart your server.
4. Modify the configuration file in `plugins/PluginAnalyse/config.yml` as needed.

## Configuration

The plugin comes with a default `config.yml` file located in `plugins/PluginAnalyse/`. The primary configuration options are:

- `block-ethanol`: Enable or disable checking for connections to "ethanol.rocks". Set this to `true` or `false`.
- `discord-webhook-url`: Set the Discord webhook URL to receive alerts when an Ethanol connection is detected and blocked.

```yaml
###################################################################

# PluginAnalyse v1.3                                              #

# made by HackV0gel                                               #

# download: https://github.com/PublicCode1337/PluginAnalyse       #

# discord: https://dsc.gg/bka                                     #

###################################################################

block-ethanol: true

discord-webhook-url: "YOUR DISCORD WEBHOOK HERE!"

```

### Discord Integration

When a connection to "ethanol.rocks" or the specified IP is detected and blocked, an alert will be sent to your specified Discord webhook. To set this up:

1. Create a Discord webhook in your server's settings.
2. Copy the webhook URL.
3. Paste the webhook URL into the `discord-webhook-url` field in the `config.yml` file.

This feature ensures that server administrators are promptly notified of potential issues, even if they are not currently online.

## Usage Example

### Example: Checking for Ethanol Connections

1. Ensure you have the required permission `analyse.checkethanol`.
2. Run the command `/analyse checkethanol`.
3. If any connections to "ethanol.rocks" are found, you will receive a red alert message. Additionally, a notification will be sent to the configured Discord webhook. Otherwise, you'll receive a green confirmation message.

### Example: Clearing the Log File

1. Ensure you have the required permission `analyse.clearlogs`.
2. Run the command `/analyse clear`.
3. The log file will be cleared, and you'll receive a green confirmation message.

## Development

### Prerequisites
- Java 21 or higher
- Gradle

### Building the Plugin

1. Clone the repository:
   ```sh
   git clone https://github.com/yourusername/PluginAnalyse.git
   ```
2. Navigate to the project directory:
   ```sh
   cd PluginAnalyse
   ```
3. Build the plugin using Maven:
   ```sh
   mvn clean package
   ```
4. The compiled `.jar` file will be located in the `build/libs/` directory.

## Contributing

Contributions are welcome! Please fork the repository, create a new branch, and submit a pull request. Ensure that your code adheres to the existing style and includes appropriate comments and documentation.

## Credits

Developed by **[HackV0gel]**.

## Contact

For questions or suggestions, please open an issue or reach out via [email](mailto:contact@cerya.de).
