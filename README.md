# PokemonTest Cloudstream plugin

This is a small Cloudstream provider used only to test HLS playback with the public Mux demo stream:

`https://test-streams.mux.dev/x36xhzz/x36xhzz.m3u8`

The title is labelled "Pokémon – Test Bölümü" only as a demo catalogue label. The stream itself is **not Pokémon footage**.

## Build

1. Upload the contents of this folder to a new GitHub repository.
2. Open the repository's **Actions** tab.
3. Run **Build Cloudstream plugin** (or push a commit to `main` / `master`).
4. Download the `PokemonTest-cs3` artifact from the completed workflow run.
5. If the artifact contains a `.cs3` file, install that file using Cloudstream's local plugin installation option.

## Notes

- This is a project source bundle, not a precompiled `.cs3` binary.
- GitHub Actions compiles it. The first build may fail if upstream Cloudstream Gradle/API dependencies have changed; check the Actions log for the exact compiler error.
- The Mux demo stream is external and may change or stop working.
