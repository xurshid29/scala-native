#define _XOPEN_SOURCE 700

#include <errno.h>
#include <ftw.h>
#include <math.h>
#include <signal.h>
#include <stdint.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>


// This file contains functions that wrap posix
// built-in macros. We need this because Scala Native
// can not expand C macros, and that's the easiest way to
// get the values out of those in a portable manner.

int scalanative_eintr() {
    return EINTR;
}
