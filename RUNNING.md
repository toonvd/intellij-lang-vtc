# Running the Varnish Test Case Syntax Highlighter Plugin

This document provides detailed instructions on how to run the Varnish Test Case Syntax Highlighter plugin for IntelliJ IDEA.

## Prerequisites

- Java Development Kit (JDK) 11 or later
- IntelliJ IDEA (Community or Ultimate edition, version 2022.2.5 or later)
- Gradle (optional, as the project includes a Gradle wrapper)

## Option 1: Running in Development Mode

This is the quickest way to test the plugin during development:

1. **Clone the repository**:
   ```
   git clone https://github.com/your-username/intellij-lang-vtc.git
   cd intellij-lang-vtc
   ```

2. **Run the plugin in development mode**:
   ```
   ./gradlew runIde
   ```
   
   This command will:
   - Build the plugin
   - Start a new instance of IntelliJ IDEA with the plugin installed
   - Allow you to test the plugin in a sandboxed environment

3. **Test the plugin**:
   - In the new IntelliJ IDEA instance, create a new file with the `.vtc` extension
   - Or open an existing `.vtc` file (there's a sample in `src/test/resources/examples/sample.vtc`)
   - Verify that syntax highlighting is working correctly

## Option 2: Building and Installing the Plugin

If you want to install the plugin in your regular IntelliJ IDEA instance:

1. **Clone the repository**:
   ```
   git clone https://github.com/your-username/intellij-lang-vtc.git
   cd intellij-lang-vtc
   ```

2. **Build the plugin**:
   ```
   ./gradlew buildPlugin
   ```
   
   This will create a plugin distribution zip file in `build/distributions/intellij-lang-vtc-1.0.0.zip`

3. **Install the plugin in IntelliJ IDEA**:
   - Open IntelliJ IDEA
   - Go to Settings/Preferences > Plugins
   - Click on the gear icon and select "Install Plugin from Disk..."
   - Navigate to and select the zip file created in step 2
   - Click "OK" and restart IntelliJ IDEA when prompted

4. **Test the plugin**:
   - Create or open a `.vtc` file
   - Verify that syntax highlighting is working correctly

## Verifying the Plugin is Working

To verify that the plugin is working correctly:

1. Open a `.vtc` file in IntelliJ IDEA
2. Check the status bar at the bottom-right corner of the editor - it should show "Varnish Test Case" as the file type
3. Verify that syntax highlighting is applied:
   - Keywords like `varnishtest`, `server`, `client` should be highlighted
   - Commands like `rxreq`, `txresp`, `expect` should be highlighted
   - Comments (lines starting with `#`) should be highlighted
   - Strings (enclosed in quotes) should be highlighted
   - Numbers, operators, and braces should be highlighted

## Troubleshooting

If you encounter any issues:

1. **Check the IntelliJ IDEA log**:
   - Help > Show Log in Explorer/Finder

2. **Verify plugin compatibility**:
   - The plugin is compatible with IntelliJ IDEA versions 2022.2.5 to 2023.3.*
   - Check your IntelliJ IDEA version under Help > About

3. **Gradle issues**:
   - Try running Gradle with the `--stacktrace` flag:
     ```
     ./gradlew buildPlugin --stacktrace
     ```

4. **Plugin not visible**:
   - Make sure the plugin is enabled in Settings/Preferences > Plugins

For more troubleshooting tips, see the [README.md](README.md) file.