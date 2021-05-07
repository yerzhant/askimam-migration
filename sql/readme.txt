Pre-prod testing:

- set imams' user ids (which are depicted by TODOs in add-imams.sql);

- run add-imams.sql;

- run this app with 'import' first param;

- run checks in checks.sql;

- if checks are ok, then exec the app with 'migrate' param.


Preps:

- create test user;

- test apk;

- upload app bundle;

- update store presence stuff.


Once the app is available:

- update it on the device and check it;

- block writes to FB db;

- run import;

- run checks in the head of checks.sql;

- start migration;

- add a message with a link to the new version.
