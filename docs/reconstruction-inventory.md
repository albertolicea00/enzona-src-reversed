# Reconstruction inventory

Run `make stats` for counts or `make inventory` to generate
`.workspace/reconstruction-inventory.json`. Python 3 is the only dependency.
The generated JSON is local and reproducible; it is not a verified mapping database.

The inventory joins every Java file under `src/` to clean files under
`app/src/main/java/` using exact `@obfuscated_class` annotations and explicit
`mappings/classes.json` entries. It keeps both sources of evidence so a conflict
does not silently replace a candidate. Source identifiers come from paths relative
to `src/`; clean names come from package declarations and filenames.
Inner classes without their own Java file and names from smali are not resolved
by this tool. It does not infer matches from short names.

Each original has a `link_status`: `unlinked`, `single_candidate`, or
`multiple_candidates`. These indicate review scope, not implementation quality.
A single candidate can still contain stubs, omitted methods, or incorrect behavior.
Generated and library classes are included in the full inventory; filter the
`original` field to `cu.xetid.apk.enzona.` for the main application package.

`mapping_issues` distinguishes absent original paths (`source_not_found`), absent
clean classes (`clean_not_found`), and disagreement between a mapping entry and a
clean file's annotation (`annotation_conflict`). These are individual incidences;
one mapping may have more than one. Resolve them against source before editing
the authoritative mapping file.

## Baseline status (pre-decompilation)

Target: `cu.xetid.apk.enzona-v20003.apk`, SHA-256
`9125313c01eb522522c2a1e312c62ad09a81193bc5b427a36b34114c701a4c0c`.

| Scope | Original Java files | Unlinked | Single candidate | Multiple candidates |
| --- | ---: | ---: | ---: | ---: |
| All `src/` | 0 | 0 | 0 | 0 |
| Main app package (`cu.xetid.apk.enzona`) | 0 | 0 | 0 | 0 |

Initial repository structure has been created prior to running `make decompile`. Once JADX output is extracted into `src/`, run `make inventory` to populate the initial source-to-clean review queue.
