/**
 * Refactoring Operations on the ibay system.
 *
 * 1. Java8-ify the collections processing code in classes.
 * 2. Add reporting mechanisms to both the client and server that doesn't block the main I/O thread,
 *    This should present a stream of events, each event representing
 *    a new bid leading the auction process. As a demonstration you should be able to print out the events
 *    and test that they are being called.
 * 3. Get the ibay server to track the average and sum of prices of bids and print out updated statistics.
 */
package com.java_8_training.problems.ibay;
