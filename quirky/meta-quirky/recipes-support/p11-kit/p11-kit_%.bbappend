
#PR_NUM is defined in local.conf...
#PR = "r${@int(PR_NUM) + 1}"

#20230417 some flatpaks have ssl error at startup.
# have found cause needs this...
PACKAGECONFIG:append = " trust-paths"
