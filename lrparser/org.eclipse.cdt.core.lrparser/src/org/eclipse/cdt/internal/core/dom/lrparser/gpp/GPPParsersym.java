/*******************************************************************************
* Copyright (c) 2006, 2008 IBM Corporation and others.
* All rights reserved. This program and the accompanying materials
* are made available under the terms of the Eclipse Public License v1.0
* which accompanies this distribution, and is available at
* http://www.eclipse.org/legal/epl-v10.html
*
* Contributors:
*     IBM Corporation - initial API and implementation
*********************************************************************************/

// This file was generated by LPG

package org.eclipse.cdt.internal.core.dom.lrparser.gpp;

public interface GPPParsersym {
    public final static int
      TK__Complex = 14,
      TK__Imaginary = 15,
      TK_restrict = 30,
      TK_asm = 5,
      TK_auto = 32,
      TK_bool = 16,
      TK_break = 83,
      TK_case = 84,
      TK_catch = 127,
      TK_char = 17,
      TK_class = 61,
      TK_const = 28,
      TK_const_cast = 48,
      TK_continue = 85,
      TK_default = 86,
      TK_delete = 75,
      TK_do = 87,
      TK_double = 18,
      TK_dynamic_cast = 49,
      TK_else = 130,
      TK_enum = 63,
      TK_explicit = 33,
      TK_export = 93,
      TK_extern = 34,
      TK_false = 50,
      TK_float = 19,
      TK_for = 88,
      TK_friend = 35,
      TK_goto = 89,
      TK_if = 90,
      TK_inline = 36,
      TK_int = 20,
      TK_long = 21,
      TK_mutable = 37,
      TK_namespace = 67,
      TK_new = 76,
      TK_operator = 9,
      TK_private = 111,
      TK_protected = 112,
      TK_public = 113,
      TK_register = 38,
      TK_reinterpret_cast = 51,
      TK_return = 91,
      TK_short = 22,
      TK_signed = 23,
      TK_sizeof = 52,
      TK_static = 39,
      TK_static_cast = 53,
      TK_struct = 64,
      TK_switch = 92,
      TK_template = 62,
      TK_this = 54,
      TK_throw = 68,
      TK_try = 79,
      TK_true = 55,
      TK_typedef = 40,
      TK_typeid = 56,
      TK_typename = 13,
      TK_union = 65,
      TK_unsigned = 24,
      TK_using = 69,
      TK_virtual = 31,
      TK_void = 25,
      TK_volatile = 29,
      TK_wchar_t = 26,
      TK_while = 82,
      TK_integer = 57,
      TK_floating = 58,
      TK_charconst = 59,
      TK_stringlit = 44,
      TK_identifier = 1,
      TK_Completion = 2,
      TK_EndOfCompletion = 11,
      TK_Invalid = 131,
      TK_LeftBracket = 70,
      TK_LeftParen = 3,
      TK_Dot = 128,
      TK_DotStar = 98,
      TK_Arrow = 114,
      TK_ArrowStar = 97,
      TK_PlusPlus = 45,
      TK_MinusMinus = 46,
      TK_And = 12,
      TK_Star = 10,
      TK_Plus = 41,
      TK_Minus = 42,
      TK_Tilde = 8,
      TK_Bang = 47,
      TK_Slash = 99,
      TK_Percent = 100,
      TK_RightShift = 94,
      TK_LeftShift = 95,
      TK_LT = 66,
      TK_GT = 78,
      TK_LE = 101,
      TK_GE = 102,
      TK_EQ = 103,
      TK_NE = 104,
      TK_Caret = 105,
      TK_Or = 106,
      TK_AndAnd = 107,
      TK_OrOr = 108,
      TK_Question = 115,
      TK_Colon = 74,
      TK_ColonColon = 4,
      TK_DotDotDot = 96,
      TK_Assign = 80,
      TK_StarAssign = 116,
      TK_SlashAssign = 117,
      TK_PercentAssign = 118,
      TK_PlusAssign = 119,
      TK_MinusAssign = 120,
      TK_RightShiftAssign = 121,
      TK_LeftShiftAssign = 122,
      TK_AndAssign = 123,
      TK_CaretAssign = 124,
      TK_OrAssign = 125,
      TK_Comma = 77,
      TK_RightBracket = 126,
      TK_RightParen = 72,
      TK_RightBrace = 81,
      TK_SemiColon = 43,
      TK_LeftBrace = 73,
      TK_typeof = 27,
      TK___alignof__ = 60,
      TK___attribute__ = 6,
      TK___declspec = 7,
      TK_MAX = 109,
      TK_MIN = 110,
      TK_ERROR_TOKEN = 71,
      TK_EOF_TOKEN = 129;

      public final static String orderedTerminalSymbols[] = {
                 "",
                 "identifier",
                 "Completion",
                 "LeftParen",
                 "ColonColon",
                 "asm",
                 "__attribute__",
                 "__declspec",
                 "Tilde",
                 "operator",
                 "Star",
                 "EndOfCompletion",
                 "And",
                 "typename",
                 "_Complex",
                 "_Imaginary",
                 "bool",
                 "char",
                 "double",
                 "float",
                 "int",
                 "long",
                 "short",
                 "signed",
                 "unsigned",
                 "void",
                 "wchar_t",
                 "typeof",
                 "const",
                 "volatile",
                 "restrict",
                 "virtual",
                 "auto",
                 "explicit",
                 "extern",
                 "friend",
                 "inline",
                 "mutable",
                 "register",
                 "static",
                 "typedef",
                 "Plus",
                 "Minus",
                 "SemiColon",
                 "stringlit",
                 "PlusPlus",
                 "MinusMinus",
                 "Bang",
                 "const_cast",
                 "dynamic_cast",
                 "false",
                 "reinterpret_cast",
                 "sizeof",
                 "static_cast",
                 "this",
                 "true",
                 "typeid",
                 "integer",
                 "floating",
                 "charconst",
                 "__alignof__",
                 "class",
                 "template",
                 "enum",
                 "struct",
                 "union",
                 "LT",
                 "namespace",
                 "throw",
                 "using",
                 "LeftBracket",
                 "ERROR_TOKEN",
                 "RightParen",
                 "LeftBrace",
                 "Colon",
                 "delete",
                 "new",
                 "Comma",
                 "GT",
                 "try",
                 "Assign",
                 "RightBrace",
                 "while",
                 "break",
                 "case",
                 "continue",
                 "default",
                 "do",
                 "for",
                 "goto",
                 "if",
                 "return",
                 "switch",
                 "export",
                 "RightShift",
                 "LeftShift",
                 "DotDotDot",
                 "ArrowStar",
                 "DotStar",
                 "Slash",
                 "Percent",
                 "LE",
                 "GE",
                 "EQ",
                 "NE",
                 "Caret",
                 "Or",
                 "AndAnd",
                 "OrOr",
                 "MAX",
                 "MIN",
                 "private",
                 "protected",
                 "public",
                 "Arrow",
                 "Question",
                 "StarAssign",
                 "SlashAssign",
                 "PercentAssign",
                 "PlusAssign",
                 "MinusAssign",
                 "RightShiftAssign",
                 "LeftShiftAssign",
                 "AndAssign",
                 "CaretAssign",
                 "OrAssign",
                 "RightBracket",
                 "catch",
                 "Dot",
                 "EOF_TOKEN",
                 "else",
                 "Invalid"
             };

    public final static boolean isValidForParser = true;
}
