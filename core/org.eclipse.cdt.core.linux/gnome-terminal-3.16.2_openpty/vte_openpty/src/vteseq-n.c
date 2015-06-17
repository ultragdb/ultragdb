/* ANSI-C code produced by gperf version 3.0.4 */
/* Command-line: gperf -m 100 vteseq-n.gperf  */
/* Computed positions: -k'2,8,12,18' */

#if !((' ' == 32) && ('!' == 33) && ('"' == 34) && ('#' == 35) \
      && ('%' == 37) && ('&' == 38) && ('\'' == 39) && ('(' == 40) \
      && (')' == 41) && ('*' == 42) && ('+' == 43) && (',' == 44) \
      && ('-' == 45) && ('.' == 46) && ('/' == 47) && ('0' == 48) \
      && ('1' == 49) && ('2' == 50) && ('3' == 51) && ('4' == 52) \
      && ('5' == 53) && ('6' == 54) && ('7' == 55) && ('8' == 56) \
      && ('9' == 57) && (':' == 58) && (';' == 59) && ('<' == 60) \
      && ('=' == 61) && ('>' == 62) && ('?' == 63) && ('A' == 65) \
      && ('B' == 66) && ('C' == 67) && ('D' == 68) && ('E' == 69) \
      && ('F' == 70) && ('G' == 71) && ('H' == 72) && ('I' == 73) \
      && ('J' == 74) && ('K' == 75) && ('L' == 76) && ('M' == 77) \
      && ('N' == 78) && ('O' == 79) && ('P' == 80) && ('Q' == 81) \
      && ('R' == 82) && ('S' == 83) && ('T' == 84) && ('U' == 85) \
      && ('V' == 86) && ('W' == 87) && ('X' == 88) && ('Y' == 89) \
      && ('Z' == 90) && ('[' == 91) && ('\\' == 92) && (']' == 93) \
      && ('^' == 94) && ('_' == 95) && ('a' == 97) && ('b' == 98) \
      && ('c' == 99) && ('d' == 100) && ('e' == 101) && ('f' == 102) \
      && ('g' == 103) && ('h' == 104) && ('i' == 105) && ('j' == 106) \
      && ('k' == 107) && ('l' == 108) && ('m' == 109) && ('n' == 110) \
      && ('o' == 111) && ('p' == 112) && ('q' == 113) && ('r' == 114) \
      && ('s' == 115) && ('t' == 116) && ('u' == 117) && ('v' == 118) \
      && ('w' == 119) && ('x' == 120) && ('y' == 121) && ('z' == 122) \
      && ('{' == 123) && ('|' == 124) && ('}' == 125) && ('~' == 126))
/* The character set is not based on ISO-646.  */
#error "gperf generated tables don't work with this execution character set. Please report a bug to <bug-gnu-gperf@gnu.org>."
#endif

#line 16 "vteseq-n.gperf"
struct vteseq_n_struct {
	int seq;
	VteTerminalSequenceHandler handler;
};
#include <string.h>
/* maximum key range = 119, duplicates = 0 */

#ifdef __GNUC__
__inline
#else
#ifdef __cplusplus
inline
#endif
#endif
static unsigned int
vteseq_n_hash (register const char *str, register unsigned int len)
{
  static const unsigned char asso_values[] =
    {
      126, 126, 126, 126, 126, 126, 126, 126, 126, 126,
      126, 126, 126, 126, 126, 126, 126, 126, 126, 126,
      126, 126, 126, 126, 126, 126, 126, 126, 126, 126,
      126, 126, 126, 126, 126, 126, 126, 126, 126, 126,
      126, 126, 126, 126, 126,   8,   9, 126, 126,  30,
       16, 126, 126, 126, 126, 126, 126, 126, 126, 126,
      126, 126, 126, 126, 126, 126, 126, 126, 126, 126,
      126, 126, 126, 126, 126, 126, 126, 126, 126, 126,
      126, 126, 126, 126, 126, 126, 126, 126, 126, 126,
      126, 126, 126, 126, 126, 126, 126,   6,  13,   9,
       15,   4,  38, 126,  11,  18,  47,   6,  50,   6,
        9,   4,   8,  35,  48,  12,  26,   7,  27, 126,
        4, 126,  20, 126, 126, 126, 126, 126, 126, 126,
      126, 126, 126, 126, 126, 126, 126, 126, 126, 126,
      126, 126, 126, 126, 126, 126, 126, 126, 126, 126,
      126, 126, 126, 126, 126, 126, 126, 126, 126, 126,
      126, 126, 126, 126, 126, 126, 126, 126, 126, 126,
      126, 126, 126, 126, 126, 126, 126, 126, 126, 126,
      126, 126, 126, 126, 126, 126, 126, 126, 126, 126,
      126, 126, 126, 126, 126, 126, 126, 126, 126, 126,
      126, 126, 126, 126, 126, 126, 126, 126, 126, 126,
      126, 126, 126, 126, 126, 126, 126, 126, 126, 126,
      126, 126, 126, 126, 126, 126, 126, 126, 126, 126,
      126, 126, 126, 126, 126, 126, 126, 126, 126, 126,
      126, 126, 126, 126, 126, 126, 126, 126, 126, 126,
      126, 126, 126, 126, 126, 126, 126
    };
  register int hval = len;

  switch (hval)
    {
      default:
        hval += asso_values[(unsigned char)str[17]];
      /*FALLTHROUGH*/
      case 17:
      case 16:
      case 15:
      case 14:
      case 13:
      case 12:
        hval += asso_values[(unsigned char)str[11]+1];
      /*FALLTHROUGH*/
      case 11:
      case 10:
      case 9:
      case 8:
        hval += asso_values[(unsigned char)str[7]];
      /*FALLTHROUGH*/
      case 7:
      case 6:
      case 5:
      case 4:
      case 3:
      case 2:
        hval += asso_values[(unsigned char)str[1]];
        break;
    }
  return hval;
}

struct vteseq_n_pool_t
  {
    char vteseq_n_pool_str7[sizeof("nop")];
    char vteseq_n_pool_str8[sizeof("bell")];
    char vteseq_n_pool_str9[sizeof("tab")];
    char vteseq_n_pool_str10[sizeof("decset")];
    char vteseq_n_pool_str13[sizeof("tab-set")];
    char vteseq_n_pool_str14[sizeof("index")];
    char vteseq_n_pool_str16[sizeof("set-mode")];
    char vteseq_n_pool_str17[sizeof("form-feed")];
    char vteseq_n_pool_str18[sizeof("reset-mode")];
    char vteseq_n_pool_str19[sizeof("reset-color")];
    char vteseq_n_pool_str21[sizeof("tab-clear")];
    char vteseq_n_pool_str22[sizeof("next-line")];
    char vteseq_n_pool_str23[sizeof("cursor-up")];
    char vteseq_n_pool_str24[sizeof("backspace")];
    char vteseq_n_pool_str25[sizeof("scroll-up")];
    char vteseq_n_pool_str26[sizeof("soft-reset")];
    char vteseq_n_pool_str27[sizeof("shift-out")];
    char vteseq_n_pool_str28[sizeof("shift-in")];
    char vteseq_n_pool_str29[sizeof("full-reset")];
    char vteseq_n_pool_str30[sizeof("save-mode")];
    char vteseq_n_pool_str31[sizeof("line-feed")];
    char vteseq_n_pool_str32[sizeof("carriage-return")];
    char vteseq_n_pool_str33[sizeof("cursor-down")];
    char vteseq_n_pool_str34[sizeof("set-icon-title")];
    char vteseq_n_pool_str35[sizeof("scroll-down")];
    char vteseq_n_pool_str36[sizeof("normal-keypad")];
    char vteseq_n_pool_str37[sizeof("cursor-position")];
    char vteseq_n_pool_str38[sizeof("decreset")];
    char vteseq_n_pool_str39[sizeof("cursor-backward")];
    char vteseq_n_pool_str41[sizeof("cursor-next-line")];
    char vteseq_n_pool_str42[sizeof("set-window-title")];
    char vteseq_n_pool_str43[sizeof("delete-characters")];
    char vteseq_n_pool_str44[sizeof("cursor-back-tab")];
    char vteseq_n_pool_str46[sizeof("set-icon-and-window-title")];
    char vteseq_n_pool_str47[sizeof("change-color-st")];
    char vteseq_n_pool_str48[sizeof("change-color-bel")];
    char vteseq_n_pool_str49[sizeof("cursor-position-top-row")];
    char vteseq_n_pool_str50[sizeof("set-scrolling-region")];
    char vteseq_n_pool_str51[sizeof("reset-foreground-color")];
    char vteseq_n_pool_str52[sizeof("restore-cursor")];
    char vteseq_n_pool_str53[sizeof("reset-background-color")];
    char vteseq_n_pool_str54[sizeof("change-cursor-color-st")];
    char vteseq_n_pool_str55[sizeof("change-cursor-color-bel")];
    char vteseq_n_pool_str56[sizeof("request-terminal-parameters")];
    char vteseq_n_pool_str57[sizeof("set-scrolling-region-to-end")];
    char vteseq_n_pool_str58[sizeof("set-cursor-style")];
    char vteseq_n_pool_str59[sizeof("character-position-absolute")];
    char vteseq_n_pool_str60[sizeof("cursor-character-absolute")];
    char vteseq_n_pool_str61[sizeof("set-scrolling-region-from-start")];
    char vteseq_n_pool_str62[sizeof("restore-mode")];
    char vteseq_n_pool_str63[sizeof("reverse-index")];
    char vteseq_n_pool_str64[sizeof("line-position-absolute")];
    char vteseq_n_pool_str65[sizeof("save-cursor")];
    char vteseq_n_pool_str66[sizeof("screen-alignment-test")];
    char vteseq_n_pool_str67[sizeof("device-status-report")];
    char vteseq_n_pool_str68[sizeof("character-attributes")];
    char vteseq_n_pool_str69[sizeof("change-background-color-st")];
    char vteseq_n_pool_str70[sizeof("change-background-color-bel")];
    char vteseq_n_pool_str71[sizeof("linux-console-cursor-attributes")];
    char vteseq_n_pool_str72[sizeof("cursor-forward")];
    char vteseq_n_pool_str73[sizeof("designate-g1-plain")];
    char vteseq_n_pool_str74[sizeof("erase-in-line")];
    char vteseq_n_pool_str75[sizeof("vertical-tab")];
    char vteseq_n_pool_str76[sizeof("application-keypad")];
    char vteseq_n_pool_str77[sizeof("change-highlight-background-color-st")];
    char vteseq_n_pool_str78[sizeof("change-highlight-background-color-bel")];
    char vteseq_n_pool_str79[sizeof("designate-g1-line-drawing")];
    char vteseq_n_pool_str80[sizeof("dec-device-status-report")];
    char vteseq_n_pool_str81[sizeof("send-primary-device-attributes")];
    char vteseq_n_pool_str82[sizeof("window-manipulation")];
    char vteseq_n_pool_str84[sizeof("designate-g1-british")];
    char vteseq_n_pool_str85[sizeof("send-secondary-device-attributes")];
    char vteseq_n_pool_str87[sizeof("designate-g0-plain")];
    char vteseq_n_pool_str88[sizeof("set-current-file-uri")];
    char vteseq_n_pool_str89[sizeof("reset-cursor-color")];
    char vteseq_n_pool_str90[sizeof("erase-characters")];
    char vteseq_n_pool_str92[sizeof("delete-lines")];
    char vteseq_n_pool_str93[sizeof("designate-g0-line-drawing")];
    char vteseq_n_pool_str94[sizeof("change-foreground-color-st")];
    char vteseq_n_pool_str95[sizeof("change-foreground-color-bel")];
    char vteseq_n_pool_str96[sizeof("cursor-forward-tabulation")];
    char vteseq_n_pool_str97[sizeof("insert-lines")];
    char vteseq_n_pool_str98[sizeof("designate-g0-british")];
    char vteseq_n_pool_str99[sizeof("erase-in-display")];
    char vteseq_n_pool_str101[sizeof("insert-blank-characters")];
    char vteseq_n_pool_str102[sizeof("change-highlight-foreground-color-st")];
    char vteseq_n_pool_str103[sizeof("change-highlight-foreground-color-bel")];
    char vteseq_n_pool_str105[sizeof("reset-highlight-foreground-color")];
    char vteseq_n_pool_str107[sizeof("reset-highlight-background-color")];
    char vteseq_n_pool_str110[sizeof("return-terminal-id")];
    char vteseq_n_pool_str112[sizeof("set-current-directory-uri")];
    char vteseq_n_pool_str124[sizeof("cursor-preceding-line")];
    char vteseq_n_pool_str125[sizeof("return-terminal-status")];
  };
static const struct vteseq_n_pool_t vteseq_n_pool_contents =
  {
    "nop",
    "bell",
    "tab",
    "decset",
    "tab-set",
    "index",
    "set-mode",
    "form-feed",
    "reset-mode",
    "reset-color",
    "tab-clear",
    "next-line",
    "cursor-up",
    "backspace",
    "scroll-up",
    "soft-reset",
    "shift-out",
    "shift-in",
    "full-reset",
    "save-mode",
    "line-feed",
    "carriage-return",
    "cursor-down",
    "set-icon-title",
    "scroll-down",
    "normal-keypad",
    "cursor-position",
    "decreset",
    "cursor-backward",
    "cursor-next-line",
    "set-window-title",
    "delete-characters",
    "cursor-back-tab",
    "set-icon-and-window-title",
    "change-color-st",
    "change-color-bel",
    "cursor-position-top-row",
    "set-scrolling-region",
    "reset-foreground-color",
    "restore-cursor",
    "reset-background-color",
    "change-cursor-color-st",
    "change-cursor-color-bel",
    "request-terminal-parameters",
    "set-scrolling-region-to-end",
    "set-cursor-style",
    "character-position-absolute",
    "cursor-character-absolute",
    "set-scrolling-region-from-start",
    "restore-mode",
    "reverse-index",
    "line-position-absolute",
    "save-cursor",
    "screen-alignment-test",
    "device-status-report",
    "character-attributes",
    "change-background-color-st",
    "change-background-color-bel",
    "linux-console-cursor-attributes",
    "cursor-forward",
    "designate-g1-plain",
    "erase-in-line",
    "vertical-tab",
    "application-keypad",
    "change-highlight-background-color-st",
    "change-highlight-background-color-bel",
    "designate-g1-line-drawing",
    "dec-device-status-report",
    "send-primary-device-attributes",
    "window-manipulation",
    "designate-g1-british",
    "send-secondary-device-attributes",
    "designate-g0-plain",
    "set-current-file-uri",
    "reset-cursor-color",
    "erase-characters",
    "delete-lines",
    "designate-g0-line-drawing",
    "change-foreground-color-st",
    "change-foreground-color-bel",
    "cursor-forward-tabulation",
    "insert-lines",
    "designate-g0-british",
    "erase-in-display",
    "insert-blank-characters",
    "change-highlight-foreground-color-st",
    "change-highlight-foreground-color-bel",
    "reset-highlight-foreground-color",
    "reset-highlight-background-color",
    "return-terminal-id",
    "set-current-directory-uri",
    "cursor-preceding-line",
    "return-terminal-status"
  };
#define vteseq_n_pool ((const char *) &vteseq_n_pool_contents)
#ifdef __GNUC__
__inline
#if defined __GNUC_STDC_INLINE__ || defined __GNUC_GNU_INLINE__
__attribute__ ((__gnu_inline__))
#endif
#endif
const struct vteseq_n_struct *
vteseq_n_lookup (register const char *str, register unsigned int len)
{
  enum
    {
      TOTAL_KEYWORDS = 93,
      MIN_WORD_LENGTH = 3,
      MAX_WORD_LENGTH = 37,
      MIN_HASH_VALUE = 7,
      MAX_HASH_VALUE = 125
    };

  static const unsigned char lengthtable[] =
    {
       0,  0,  0,  0,  0,  0,  0,  3,  4,  3,  6,  0,  0,  7,
       5,  0,  8,  9, 10, 11,  0,  9,  9,  9,  9,  9, 10,  9,
       8, 10,  9,  9, 15, 11, 14, 11, 13, 15,  8, 15,  0, 16,
      16, 17, 15,  0, 25, 15, 16, 23, 20, 22, 14, 22, 22, 23,
      27, 27, 16, 27, 25, 31, 12, 13, 22, 11, 21, 20, 20, 26,
      27, 31, 14, 18, 13, 12, 18, 36, 37, 25, 24, 30, 19,  0,
      20, 32,  0, 18, 20, 18, 16,  0, 12, 25, 26, 27, 25, 12,
      20, 16,  0, 23, 36, 37,  0, 32,  0, 32,  0,  0, 18,  0,
      25,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 21, 22
    };
  static const struct vteseq_n_struct wordlist[] =
    {
      {-1}, {-1}, {-1}, {-1}, {-1}, {-1}, {-1},
#line 22 "vteseq-n.gperf"
      {(int)(long)&((struct vteseq_n_pool_t *)0)->vteseq_n_pool_str7, VTE_SEQUENCE_HANDLER(vte_sequence_handler_nop)},
#line 25 "vteseq-n.gperf"
      {(int)(long)&((struct vteseq_n_pool_t *)0)->vteseq_n_pool_str8, VTE_SEQUENCE_HANDLER(vte_sequence_handler_bell)},
#line 27 "vteseq-n.gperf"
      {(int)(long)&((struct vteseq_n_pool_t *)0)->vteseq_n_pool_str9, VTE_SEQUENCE_HANDLER(vte_sequence_handler_tab)},
#line 29 "vteseq-n.gperf"
      {(int)(long)&((struct vteseq_n_pool_t *)0)->vteseq_n_pool_str10, VTE_SEQUENCE_HANDLER(vte_sequence_handler_decset)},
      {-1}, {-1},
#line 31 "vteseq-n.gperf"
      {(int)(long)&((struct vteseq_n_pool_t *)0)->vteseq_n_pool_str13, VTE_SEQUENCE_HANDLER(vte_sequence_handler_tab_set)},
#line 28 "vteseq-n.gperf"
      {(int)(long)&((struct vteseq_n_pool_t *)0)->vteseq_n_pool_str14, VTE_SEQUENCE_HANDLER(vte_sequence_handler_index)},
      {-1},
#line 33 "vteseq-n.gperf"
      {(int)(long)&((struct vteseq_n_pool_t *)0)->vteseq_n_pool_str16, VTE_SEQUENCE_HANDLER(vte_sequence_handler_set_mode)},
#line 35 "vteseq-n.gperf"
      {(int)(long)&((struct vteseq_n_pool_t *)0)->vteseq_n_pool_str17, VTE_SEQUENCE_HANDLER(vte_sequence_handler_form_feed)},
#line 42 "vteseq-n.gperf"
      {(int)(long)&((struct vteseq_n_pool_t *)0)->vteseq_n_pool_str18, VTE_SEQUENCE_HANDLER(vte_sequence_handler_reset_mode)},
#line 50 "vteseq-n.gperf"
      {(int)(long)&((struct vteseq_n_pool_t *)0)->vteseq_n_pool_str19, VTE_SEQUENCE_HANDLER(vte_sequence_handler_reset_color)},
      {-1},
#line 39 "vteseq-n.gperf"
      {(int)(long)&((struct vteseq_n_pool_t *)0)->vteseq_n_pool_str21, VTE_SEQUENCE_HANDLER(vte_sequence_handler_tab_clear)},
#line 36 "vteseq-n.gperf"
      {(int)(long)&((struct vteseq_n_pool_t *)0)->vteseq_n_pool_str22, VTE_SEQUENCE_HANDLER(vte_sequence_handler_next_line)},
#line 34 "vteseq-n.gperf"
      {(int)(long)&((struct vteseq_n_pool_t *)0)->vteseq_n_pool_str23, VTE_SEQUENCE_HANDLER(vte_sequence_handler_cursor_up)},
#line 26 "vteseq-n.gperf"
      {(int)(long)&((struct vteseq_n_pool_t *)0)->vteseq_n_pool_str24, VTE_SEQUENCE_HANDLER(vte_sequence_handler_backspace)},
#line 38 "vteseq-n.gperf"
      {(int)(long)&((struct vteseq_n_pool_t *)0)->vteseq_n_pool_str25, VTE_SEQUENCE_HANDLER(vte_sequence_handler_scroll_up)},
#line 43 "vteseq-n.gperf"
      {(int)(long)&((struct vteseq_n_pool_t *)0)->vteseq_n_pool_str26, VTE_SEQUENCE_HANDLER(vte_sequence_handler_soft_reset)},
#line 129 "vteseq-n.gperf"
      {(int)(long)&((struct vteseq_n_pool_t *)0)->vteseq_n_pool_str27, VTE_SEQUENCE_HANDLER(vte_sequence_handler_shift_out)},
#line 128 "vteseq-n.gperf"
      {(int)(long)&((struct vteseq_n_pool_t *)0)->vteseq_n_pool_str28, VTE_SEQUENCE_HANDLER(vte_sequence_handler_shift_in)},
#line 40 "vteseq-n.gperf"
      {(int)(long)&((struct vteseq_n_pool_t *)0)->vteseq_n_pool_str29, VTE_SEQUENCE_HANDLER(vte_sequence_handler_full_reset)},
#line 37 "vteseq-n.gperf"
      {(int)(long)&((struct vteseq_n_pool_t *)0)->vteseq_n_pool_str30, VTE_SEQUENCE_HANDLER(vte_sequence_handler_save_mode)},
#line 23 "vteseq-n.gperf"
      {(int)(long)&((struct vteseq_n_pool_t *)0)->vteseq_n_pool_str31, VTE_SEQUENCE_HANDLER(vte_sequence_handler_line_feed)},
#line 24 "vteseq-n.gperf"
      {(int)(long)&((struct vteseq_n_pool_t *)0)->vteseq_n_pool_str32, VTE_SEQUENCE_HANDLER(vte_sequence_handler_carriage_return)},
#line 44 "vteseq-n.gperf"
      {(int)(long)&((struct vteseq_n_pool_t *)0)->vteseq_n_pool_str33, VTE_SEQUENCE_HANDLER(vte_sequence_handler_cursor_down)},
#line 67 "vteseq-n.gperf"
      {(int)(long)&((struct vteseq_n_pool_t *)0)->vteseq_n_pool_str34, VTE_SEQUENCE_HANDLER(vte_sequence_handler_set_icon_title)},
#line 47 "vteseq-n.gperf"
      {(int)(long)&((struct vteseq_n_pool_t *)0)->vteseq_n_pool_str35, VTE_SEQUENCE_HANDLER(vte_sequence_handler_scroll_down)},
#line 59 "vteseq-n.gperf"
      {(int)(long)&((struct vteseq_n_pool_t *)0)->vteseq_n_pool_str36, VTE_SEQUENCE_HANDLER(vte_sequence_handler_normal_keypad)},
#line 70 "vteseq-n.gperf"
      {(int)(long)&((struct vteseq_n_pool_t *)0)->vteseq_n_pool_str37, VTE_SEQUENCE_HANDLER(vte_sequence_handler_cursor_position)},
#line 32 "vteseq-n.gperf"
      {(int)(long)&((struct vteseq_n_pool_t *)0)->vteseq_n_pool_str38, VTE_SEQUENCE_HANDLER(vte_sequence_handler_decreset)},
#line 69 "vteseq-n.gperf"
      {(int)(long)&((struct vteseq_n_pool_t *)0)->vteseq_n_pool_str39, VTE_SEQUENCE_HANDLER(vte_sequence_handler_cursor_backward)},
      {-1},
#line 75 "vteseq-n.gperf"
      {(int)(long)&((struct vteseq_n_pool_t *)0)->vteseq_n_pool_str41, VTE_SEQUENCE_HANDLER(vte_sequence_handler_cursor_next_line)},
#line 78 "vteseq-n.gperf"
      {(int)(long)&((struct vteseq_n_pool_t *)0)->vteseq_n_pool_str42, VTE_SEQUENCE_HANDLER(vte_sequence_handler_set_window_title)},
#line 81 "vteseq-n.gperf"
      {(int)(long)&((struct vteseq_n_pool_t *)0)->vteseq_n_pool_str43, VTE_SEQUENCE_HANDLER(vte_sequence_handler_delete_characters)},
#line 68 "vteseq-n.gperf"
      {(int)(long)&((struct vteseq_n_pool_t *)0)->vteseq_n_pool_str44, VTE_SEQUENCE_HANDLER(vte_sequence_handler_cursor_back_tab)},
      {-1},
#line 142 "vteseq-n.gperf"
      {(int)(long)&((struct vteseq_n_pool_t *)0)->vteseq_n_pool_str46, VTE_SEQUENCE_HANDLER(vte_sequence_handler_set_icon_and_window_title)},
#line 49 "vteseq-n.gperf"
      {(int)(long)&((struct vteseq_n_pool_t *)0)->vteseq_n_pool_str47, VTE_SEQUENCE_HANDLER(vte_sequence_handler_change_color_st)},
#line 48 "vteseq-n.gperf"
      {(int)(long)&((struct vteseq_n_pool_t *)0)->vteseq_n_pool_str48, VTE_SEQUENCE_HANDLER(vte_sequence_handler_change_color_bel)},
#line 71 "vteseq-n.gperf"
      {(int)(long)&((struct vteseq_n_pool_t *)0)->vteseq_n_pool_str49, VTE_SEQUENCE_HANDLER(vte_sequence_handler_cursor_position_top_row)},
#line 93 "vteseq-n.gperf"
      {(int)(long)&((struct vteseq_n_pool_t *)0)->vteseq_n_pool_str50, VTE_SEQUENCE_HANDLER(vte_sequence_handler_set_scrolling_region)},
#line 135 "vteseq-n.gperf"
      {(int)(long)&((struct vteseq_n_pool_t *)0)->vteseq_n_pool_str51, VTE_SEQUENCE_HANDLER(vte_sequence_handler_reset_foreground_color)},
#line 66 "vteseq-n.gperf"
      {(int)(long)&((struct vteseq_n_pool_t *)0)->vteseq_n_pool_str52, VTE_SEQUENCE_HANDLER(vte_sequence_handler_restore_cursor)},
#line 132 "vteseq-n.gperf"
      {(int)(long)&((struct vteseq_n_pool_t *)0)->vteseq_n_pool_str53, VTE_SEQUENCE_HANDLER(vte_sequence_handler_reset_background_color)},
#line 89 "vteseq-n.gperf"
      {(int)(long)&((struct vteseq_n_pool_t *)0)->vteseq_n_pool_str54, VTE_SEQUENCE_HANDLER(vte_sequence_handler_change_cursor_color_st)},
#line 88 "vteseq-n.gperf"
      {(int)(long)&((struct vteseq_n_pool_t *)0)->vteseq_n_pool_str55, VTE_SEQUENCE_HANDLER(vte_sequence_handler_change_cursor_color_bel)},
#line 145 "vteseq-n.gperf"
      {(int)(long)&((struct vteseq_n_pool_t *)0)->vteseq_n_pool_str56, VTE_SEQUENCE_HANDLER(vte_sequence_handler_request_terminal_parameters)},
#line 95 "vteseq-n.gperf"
      {(int)(long)&((struct vteseq_n_pool_t *)0)->vteseq_n_pool_str57, VTE_SEQUENCE_HANDLER(vte_sequence_handler_set_scrolling_region_to_end)},
#line 79 "vteseq-n.gperf"
      {(int)(long)&((struct vteseq_n_pool_t *)0)->vteseq_n_pool_str58, VTE_SEQUENCE_HANDLER(vte_sequence_handler_set_cursor_style)},
#line 144 "vteseq-n.gperf"
      {(int)(long)&((struct vteseq_n_pool_t *)0)->vteseq_n_pool_str59, VTE_SEQUENCE_HANDLER(vte_sequence_handler_character_position_absolute)},
#line 139 "vteseq-n.gperf"
      {(int)(long)&((struct vteseq_n_pool_t *)0)->vteseq_n_pool_str60, VTE_SEQUENCE_HANDLER(vte_sequence_handler_cursor_character_absolute)},
#line 94 "vteseq-n.gperf"
      {(int)(long)&((struct vteseq_n_pool_t *)0)->vteseq_n_pool_str61, VTE_SEQUENCE_HANDLER(vte_sequence_handler_set_scrolling_region_from_start)},
#line 54 "vteseq-n.gperf"
      {(int)(long)&((struct vteseq_n_pool_t *)0)->vteseq_n_pool_str62, VTE_SEQUENCE_HANDLER(vte_sequence_handler_restore_mode)},
#line 60 "vteseq-n.gperf"
      {(int)(long)&((struct vteseq_n_pool_t *)0)->vteseq_n_pool_str63, VTE_SEQUENCE_HANDLER(vte_sequence_handler_reverse_index)},
#line 105 "vteseq-n.gperf"
      {(int)(long)&((struct vteseq_n_pool_t *)0)->vteseq_n_pool_str64, VTE_SEQUENCE_HANDLER(vte_sequence_handler_line_position_absolute)},
#line 46 "vteseq-n.gperf"
      {(int)(long)&((struct vteseq_n_pool_t *)0)->vteseq_n_pool_str65, VTE_SEQUENCE_HANDLER(vte_sequence_handler_save_cursor)},
#line 100 "vteseq-n.gperf"
      {(int)(long)&((struct vteseq_n_pool_t *)0)->vteseq_n_pool_str66, VTE_SEQUENCE_HANDLER(vte_sequence_handler_screen_alignment_test)},
#line 92 "vteseq-n.gperf"
      {(int)(long)&((struct vteseq_n_pool_t *)0)->vteseq_n_pool_str67, VTE_SEQUENCE_HANDLER(vte_sequence_handler_device_status_report)},
#line 91 "vteseq-n.gperf"
      {(int)(long)&((struct vteseq_n_pool_t *)0)->vteseq_n_pool_str68, VTE_SEQUENCE_HANDLER(vte_sequence_handler_character_attributes)},
#line 131 "vteseq-n.gperf"
      {(int)(long)&((struct vteseq_n_pool_t *)0)->vteseq_n_pool_str69, VTE_SEQUENCE_HANDLER(vte_sequence_handler_change_background_color_st)},
#line 130 "vteseq-n.gperf"
      {(int)(long)&((struct vteseq_n_pool_t *)0)->vteseq_n_pool_str70, VTE_SEQUENCE_HANDLER(vte_sequence_handler_change_background_color_bel)},
#line 161 "vteseq-n.gperf"
      {(int)(long)&((struct vteseq_n_pool_t *)0)->vteseq_n_pool_str71, VTE_SEQUENCE_HANDLER(vte_sequence_handler_linux_console_cursor_attributes)},
#line 64 "vteseq-n.gperf"
      {(int)(long)&((struct vteseq_n_pool_t *)0)->vteseq_n_pool_str72, VTE_SEQUENCE_HANDLER(vte_sequence_handler_cursor_forward)},
#line 125 "vteseq-n.gperf"
      {(int)(long)&((struct vteseq_n_pool_t *)0)->vteseq_n_pool_str73, VTE_SEQUENCE_HANDLER(vte_sequence_handler_designate_g1_plain)},
#line 57 "vteseq-n.gperf"
      {(int)(long)&((struct vteseq_n_pool_t *)0)->vteseq_n_pool_str74, VTE_SEQUENCE_HANDLER(vte_sequence_handler_erase_in_line)},
#line 56 "vteseq-n.gperf"
      {(int)(long)&((struct vteseq_n_pool_t *)0)->vteseq_n_pool_str75, VTE_SEQUENCE_HANDLER(vte_sequence_handler_vertical_tab)},
#line 82 "vteseq-n.gperf"
      {(int)(long)&((struct vteseq_n_pool_t *)0)->vteseq_n_pool_str76, VTE_SEQUENCE_HANDLER(vte_sequence_handler_application_keypad)},
#line 108 "vteseq-n.gperf"
      {(int)(long)&((struct vteseq_n_pool_t *)0)->vteseq_n_pool_str77, VTE_SEQUENCE_HANDLER(vte_sequence_handler_change_highlight_background_color_st)},
#line 107 "vteseq-n.gperf"
      {(int)(long)&((struct vteseq_n_pool_t *)0)->vteseq_n_pool_str78, VTE_SEQUENCE_HANDLER(vte_sequence_handler_change_highlight_background_color_bel)},
#line 126 "vteseq-n.gperf"
      {(int)(long)&((struct vteseq_n_pool_t *)0)->vteseq_n_pool_str79, VTE_SEQUENCE_HANDLER(vte_sequence_handler_designate_g1_line_drawing)},
#line 136 "vteseq-n.gperf"
      {(int)(long)&((struct vteseq_n_pool_t *)0)->vteseq_n_pool_str80, VTE_SEQUENCE_HANDLER(vte_sequence_handler_dec_device_status_report)},
#line 160 "vteseq-n.gperf"
      {(int)(long)&((struct vteseq_n_pool_t *)0)->vteseq_n_pool_str81, VTE_SEQUENCE_HANDLER(vte_sequence_handler_send_primary_device_attributes)},
#line 87 "vteseq-n.gperf"
      {(int)(long)&((struct vteseq_n_pool_t *)0)->vteseq_n_pool_str82, VTE_SEQUENCE_HANDLER(vte_sequence_handler_window_manipulation)},
      {-1},
#line 127 "vteseq-n.gperf"
      {(int)(long)&((struct vteseq_n_pool_t *)0)->vteseq_n_pool_str84, VTE_SEQUENCE_HANDLER(vte_sequence_handler_designate_g1_british)},
#line 162 "vteseq-n.gperf"
      {(int)(long)&((struct vteseq_n_pool_t *)0)->vteseq_n_pool_str85, VTE_SEQUENCE_HANDLER(vte_sequence_handler_send_secondary_device_attributes)},
      {-1},
#line 122 "vteseq-n.gperf"
      {(int)(long)&((struct vteseq_n_pool_t *)0)->vteseq_n_pool_str87, VTE_SEQUENCE_HANDLER(vte_sequence_handler_designate_g0_plain)},
#line 170 "vteseq-n.gperf"
      {(int)(long)&((struct vteseq_n_pool_t *)0)->vteseq_n_pool_str88, VTE_SEQUENCE_HANDLER(vte_sequence_handler_set_current_file_uri)},
#line 90 "vteseq-n.gperf"
      {(int)(long)&((struct vteseq_n_pool_t *)0)->vteseq_n_pool_str89, VTE_SEQUENCE_HANDLER(vte_sequence_handler_reset_cursor_color)},
#line 76 "vteseq-n.gperf"
      {(int)(long)&((struct vteseq_n_pool_t *)0)->vteseq_n_pool_str90, VTE_SEQUENCE_HANDLER(vte_sequence_handler_erase_characters)},
      {-1},
#line 51 "vteseq-n.gperf"
      {(int)(long)&((struct vteseq_n_pool_t *)0)->vteseq_n_pool_str92, VTE_SEQUENCE_HANDLER(vte_sequence_handler_delete_lines)},
#line 123 "vteseq-n.gperf"
      {(int)(long)&((struct vteseq_n_pool_t *)0)->vteseq_n_pool_str93, VTE_SEQUENCE_HANDLER(vte_sequence_handler_designate_g0_line_drawing)},
#line 134 "vteseq-n.gperf"
      {(int)(long)&((struct vteseq_n_pool_t *)0)->vteseq_n_pool_str94, VTE_SEQUENCE_HANDLER(vte_sequence_handler_change_foreground_color_st)},
#line 133 "vteseq-n.gperf"
      {(int)(long)&((struct vteseq_n_pool_t *)0)->vteseq_n_pool_str95, VTE_SEQUENCE_HANDLER(vte_sequence_handler_change_foreground_color_bel)},
#line 140 "vteseq-n.gperf"
      {(int)(long)&((struct vteseq_n_pool_t *)0)->vteseq_n_pool_str96, VTE_SEQUENCE_HANDLER(vte_sequence_handler_cursor_forward_tabulation)},
#line 53 "vteseq-n.gperf"
      {(int)(long)&((struct vteseq_n_pool_t *)0)->vteseq_n_pool_str97, VTE_SEQUENCE_HANDLER(vte_sequence_handler_insert_lines)},
#line 124 "vteseq-n.gperf"
      {(int)(long)&((struct vteseq_n_pool_t *)0)->vteseq_n_pool_str98, VTE_SEQUENCE_HANDLER(vte_sequence_handler_designate_g0_british)},
#line 77 "vteseq-n.gperf"
      {(int)(long)&((struct vteseq_n_pool_t *)0)->vteseq_n_pool_str99, VTE_SEQUENCE_HANDLER(vte_sequence_handler_erase_in_display)},
      {-1},
#line 114 "vteseq-n.gperf"
      {(int)(long)&((struct vteseq_n_pool_t *)0)->vteseq_n_pool_str101, VTE_SEQUENCE_HANDLER(vte_sequence_handler_insert_blank_characters)},
#line 111 "vteseq-n.gperf"
      {(int)(long)&((struct vteseq_n_pool_t *)0)->vteseq_n_pool_str102, VTE_SEQUENCE_HANDLER(vte_sequence_handler_change_highlight_foreground_color_st)},
#line 110 "vteseq-n.gperf"
      {(int)(long)&((struct vteseq_n_pool_t *)0)->vteseq_n_pool_str103, VTE_SEQUENCE_HANDLER(vte_sequence_handler_change_highlight_foreground_color_bel)},
      {-1},
#line 112 "vteseq-n.gperf"
      {(int)(long)&((struct vteseq_n_pool_t *)0)->vteseq_n_pool_str105, VTE_SEQUENCE_HANDLER(vte_sequence_handler_reset_highlight_foreground_color)},
      {-1},
#line 109 "vteseq-n.gperf"
      {(int)(long)&((struct vteseq_n_pool_t *)0)->vteseq_n_pool_str107, VTE_SEQUENCE_HANDLER(vte_sequence_handler_reset_highlight_background_color)},
      {-1}, {-1},
#line 84 "vteseq-n.gperf"
      {(int)(long)&((struct vteseq_n_pool_t *)0)->vteseq_n_pool_str110, VTE_SEQUENCE_HANDLER(vte_sequence_handler_return_terminal_id)},
      {-1},
#line 169 "vteseq-n.gperf"
      {(int)(long)&((struct vteseq_n_pool_t *)0)->vteseq_n_pool_str112, VTE_SEQUENCE_HANDLER(vte_sequence_handler_set_current_directory_uri)},
      {-1}, {-1}, {-1}, {-1}, {-1}, {-1}, {-1}, {-1}, {-1},
      {-1}, {-1},
#line 98 "vteseq-n.gperf"
      {(int)(long)&((struct vteseq_n_pool_t *)0)->vteseq_n_pool_str124, VTE_SEQUENCE_HANDLER(vte_sequence_handler_cursor_preceding_line)},
#line 106 "vteseq-n.gperf"
      {(int)(long)&((struct vteseq_n_pool_t *)0)->vteseq_n_pool_str125, VTE_SEQUENCE_HANDLER(vte_sequence_handler_return_terminal_status)}
    };

  if (len <= MAX_WORD_LENGTH && len >= MIN_WORD_LENGTH)
    {
      register int key = vteseq_n_hash (str, len);

      if (key <= MAX_HASH_VALUE && key >= 0)
        if (len == lengthtable[key])
          {
            register const char *s = wordlist[key].seq + vteseq_n_pool;

            if (*str == *s && !memcmp (str + 1, s + 1, len - 1))
              return &wordlist[key];
          }
    }
  return 0;
}
