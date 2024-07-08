
---

<p align="center">
<a href="https://github.com/Niix-Dan/StatusCord/releases/latest"><img src="https://img.shields.io/github/v/release/Niix-Dan/StatusCord.svg"></a>
<a href="https://github.com/Niix-Dan/StatusCord/releases/latest"><img src="https://img.shields.io/github/downloads/Niix-Dan/StatusCord/latest/total"></a>
<img src="https://img.shields.io/github/languages/code-size/Niix-Dan/StatusCord.svg"></a>
<a href="https://github.com/Niix-Dan/StatusCord/graphs/contributors"><img src="https://img.shields.io/github/contributors/Niix-Dan/StatusCord.svg"></a>
<a href="https://github.com/Niix-Dan/StatusCord/stargazers"><img src="https://img.shields.io/github/stars/Niix-Dan/StatusCord.svg?label=Stars&logo=github"></a>
</p>

<div align="center">

# StatusCord
| Download | Link |
| ------------- | ------------- |
| Latest Version | [Click Here](https://github.com/Niix-Dan/StatusCord/releases/latest/download/StatusCord.jar) |
| Releases | [Click Here](https://github.com/Niix-Dan/StatusCord/releases) |
| Build Artifacts | [Click Here](https://github.com/Niix-Dan/StatusCord/actions/workflows/maven-build.yml) |

</div>

---

<div align="center">

## Description
</div>
StatusCord is a Minecraft plugin that integrates with Discord to provide real-time status updates and notifications. Utilizing the JDA (Java Discord API) and Spigot API, StatusCord can send, edit, and delete messages in a Discord channel based on activities occurring in the Minecraft server.

### Features
- Real-time server status updates in Discord.
- PlaceholderAPI Support
- Easy configuration and setup.

### Usage
1. Download the latest version of StatusCord from the [Releases](https://github.com/Niix-Dan/StatusCord/releases) page.
2. Place the downloaded `StatusCord.jar` file in your Minecraft server's `plugins` directory.
3. Start your server to generate the default configuration files.
4. Edit the configuration files to customize the plugin to your liking.
5. Restart your server to apply the changes.

---

<div align="center">

## Placeholders

---

#### Discord Placeholders

| Placeholder | Description |
|-------------|-------------|
| {CpuModel} | The CPU model. |
| {ProcessLoad} | Current process load. |
| {ProcessLoad-10s} | 10 seconds process load average. |
| {ProcessLoad-1m} | 1 minute process load average. |
| {ProcessLoad-15m} | 15 minutes process load average. |
| {SystemLoad} | Current system load. |
| {SystemLoad-10s} | 10 seconds system load average. |
| {SystemLoad-1m} | 1 minute system load average. |
| {SystemLoad-15m} | 15 minutes system load average. |
| {DiskUsed} | Used disk space. |
| {DiskTotal} | Total disk space. |
| {DiskFree} | Free disk space. |
| {UsedPhysical} | Used physical memory. |
| {TotalPhysical} | Total physical memory. |
| {FreePhysical} | Free physical memory. |
| {UsedSwap} | Used swap memory. |
| {TotalSwap} | Total swap memory. |
| {FreeSwap} | Free swap memory. |
| {VirtualMemory} | Total virtual memory. |
| {ServerTps-5s} | Average TPS for the last 5 seconds. |
| {ServerTps-10s} | Average TPS for the last 10 seconds. |
| {ServerTps-1m} | Average TPS for the last 1 minute. |
| {ServerTps-5m} | Average TPS for the last 5 minutes. |
| {ServerTps-15m} | Average TPS for the last 15 minutes. |
| {OnlinePlayers} | Number of players currently online. |
| {OfflinePlayers} | Number of offline players. |
| {ServerLimit} | Maximum player limit on the server. |
| {Uptime} | Server uptime. |

#### PlaceholderAPI Placeholders

| Placeholder | Description |
|-------------|-------------|
| %stcord_cpumodel% | The CPU model. |
| %stcord_procload% | Current process load. |
| %stcord_procload_10s% | 10 seconds process load average. |
| %stcord_procload_1m% | 1 minute process load average. |
| %stcord_procload_15m% | 15 minutes process load average. |
| %stcord_sysload% | Current system load. |
| %stcord_sysload_10s% | 10 seconds system load average. |
| %stcord_sysload_1m% | 1 minute system load average. |
| %stcord_sysload_15m% | 15 minutes system load average. |
| %stcord_disk_used% | Used disk space. |
| %stcord_disk_total% | Total disk space. |
| %stcord_disk_free% | Free disk space. |
| %stcord_physical_used% | Used physical memory. |
| %stcord_physical_total% | Total physical memory. |
| %stcord_physical_free% | Free physical memory. |
| %stcord_swap_used% | Used swap memory. |
| %stcord_swap_total% | Total swap memory. |
| %stcord_swap_free% | Free swap memory. |
| %stcord_virtual_total% | Total virtual memory. |
| %stcord_tps_5s% | Average TPS for the last 5 seconds. |
| %stcord_tps_10s% | Average TPS for the last 10 seconds. |
| %stcord_tps_1m% | Average TPS for the last 1 minute. |
| %stcord_tps_5m% | Average TPS for the last 5 minutes. |
| %stcord_tps_15m% | Average TPS for the last 15 minutes. |
| %stcord_online% | Number of players currently online. |
| %stcord_max% | Maximum player limit on the server. |
</div>

---

### License and Attribution
This plugin includes pre-existing classes from the Spark plugin, which are publicly licensed for modification and redistribution. We have adapted these classes to fit the specific needs of StatusCord.

For more information about the Spark plugin and its license, visit the [Spark GitHub repository](https://github.com/lucko/spark).

## Contributing
Contributions are welcome! Please fork the repository and submit pull requests.

## Issues
If you encounter any issues or have suggestions, please report them on the [Issues](https://github.com/Niix-Dan/StatusCord/issues) page.

## License
This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

