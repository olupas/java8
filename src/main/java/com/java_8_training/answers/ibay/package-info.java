/**
 * Refactoring Operations on the ibay system.
 *
 * 1. Java8-ify the collections processing code in classes.
 *  a. make the auction class return Optional instead of null for the leader.
 *  b. the select method within Server.
 *
 * 2. Add reporting mechanisms to the server that doesn't block the main I/O thread,
 *    This should present a stream of events, each event representing
 *    a new bid leading the auction process. As a demonstration you should be able to print out the events
 *    and test that they are being called.
 *  a. Add in the callback to the server
 *  b. modify tests to check it gets called
 *  c. check it gets called on a different thread
 *  d. sync with the callback
 *  e. Maybe try using RxJava. It can require quite a refactor IMO.
 *
 * 3. Get the ibay server to track the average and sum of prices of bids and print out updated statistics.
 *  a. see BidStatisticsConsumer
 */
package com.java_8_training.answers.ibay;
