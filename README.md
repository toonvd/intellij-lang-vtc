# Varnish Test Case Syntax Highlighter

An IntelliJ IDEA plugin that provides syntax highlighting for Varnish Test Case (.vtc) files.

## Features

- Syntax highlighting for .vtc files
- Recognition of common VTC keywords and commands
- Highlighting of comments, strings, numbers, operators, and braces

## Installation

### From JetBrains Marketplace

1. Open IntelliJ IDEA
2. Go to Settings/Preferences > Plugins
3. Click on "Marketplace"
4. Search for "Varnish Test Case"
5. Click "Install"

### Manual Installation

1. Download the latest release from the [Releases](https://github.com/your-username/intellij-lang-vtc/releases) page
2. Open IntelliJ IDEA
3. Go to Settings/Preferences > Plugins
4. Click on the gear icon and select "Install Plugin from Disk..."
5. Navigate to the downloaded .jar file and select it
6. Restart IntelliJ IDEA

## Building from Source

1. Clone the repository:
   ```
   git clone https://github.com/your-username/intellij-lang-vtc.git
   ```

2. Make sure you have JDK 11 or later installed:
   ```
   java -version
   ```

3. Build the plugin:
   ```
   cd intellij-lang-vtc
   ./gradlew buildPlugin
   ```

4. The plugin will be built in `build/distributions/intellij-lang-vtc-1.0.0.zip`

## Running in Development Mode

To run and test the plugin in a development environment:

1. Open the project in IntelliJ IDEA
2. Run the Gradle task `runIde`:
   ```
   ./gradlew runIde
   ```
   This will start a new instance of IntelliJ IDEA with the plugin installed.

3. In the new IntelliJ IDEA instance, create or open a .vtc file to test the syntax highlighting.

For detailed step-by-step instructions on running the plugin, see [RUNNING.md](RUNNING.md).

## Usage

After installing the plugin, .vtc files will automatically be recognized and syntax highlighted in IntelliJ IDEA. 

To use the plugin:

1. Open or create a file with the .vtc extension in IntelliJ IDEA
2. The file should automatically be recognized as a Varnish Test Case file
3. Syntax highlighting will be applied to:
   - Keywords (varnishtest, server, client, etc.)
   - Commands (rxreq, txresp, expect, etc.)
   - Comments (lines starting with #)
   - Strings (enclosed in quotes)
   - Numbers
   - Operators and braces

## Example

See the [sample.vtc](src/test/resources/examples/sample.vtc) file for an example of syntax highlighting.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Troubleshooting

### Common Issues

1. **Gradle build fails**
   - Make sure you have JDK 11 or later installed
   - Try running with the `--stacktrace` flag: `./gradlew buildPlugin --stacktrace`
   - Check that you have internet access to download dependencies

2. **Plugin doesn't appear in IntelliJ IDEA**
   - Verify that the plugin was installed correctly
   - Check if your IntelliJ IDEA version is compatible (2022.2.5 to 2023.3.*)
   - Try restarting IntelliJ IDEA

3. **Syntax highlighting doesn't work**
   - Make sure the file has a .vtc extension
   - Try reopening the file
   - Check if the file is recognized as a Varnish Test Case file in the bottom-right corner of the editor

### Reporting Issues

If you encounter any issues not covered here, please report them on the [GitHub Issues](https://github.com/your-username/intellij-lang-vtc/issues) page.

## Contributing

Contributions are welcome! Please feel free to submit a Pull Request.
