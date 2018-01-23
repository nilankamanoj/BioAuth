# BioAuth
use keystroke dinamics to authenticate as a bio metric

Key word is required to authenticate.
Keystroke dynamics of keyword are recorded in signup as an average of three times.
Key press times for every letter and inter-key times between all combinations are considered.
All authentication details are stored in embedded database.
Users are allowed only to attempt 5 consecutive unsuccessful trials, then they have to wait 20 seconds.
For successful logins, average value sequences of database are updated to adapt user improvements in typing same keyword.
Because of adaptation behavior, success rate of login for authorized users are increasing and unauthorized access rate decreases.
