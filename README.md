RegisterMachine
===============

A register machine that can increment, decrement and check for zero.

Instructions take the form:

+R,L to increment register R then jump to instruction L.

-R,L1,L2 if R > 0 then decrement R and jump to instruction L1 else jump to instruction L2

HALT to halt

Instructions are numbered from 0 in the order they are written.

You need to configure the machine manually using RegisterMachine.configure(int...).
