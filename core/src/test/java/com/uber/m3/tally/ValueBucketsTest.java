// Copyright (c) 2017 Uber Technologies, Inc.
//
// Permission is hereby granted, free of charge, to any person obtaining a copy
// of this software and associated documentation files (the "Software"), to deal
// in the Software without restriction, including without limitation the rights
// to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
// copies of the Software, and to permit persons to whom the Software is
// furnished to do so, subject to the following conditions:
//
// The above copyright notice and this permission notice shall be included in
// all copies or substantial portions of the Software.
//
// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
// IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
// FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
// AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
// LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
// OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
// THE SOFTWARE.

package com.uber.m3.tally;

import com.uber.m3.util.Duration;
import org.junit.Test;

import static org.junit.Assert.*;

public class ValueBucketsTest {
    @Test
    public void asValues() {
        ValueBuckets buckets = new ValueBuckets(new Double[]{
            10d,
            30d,
            55d,
            200d,
            1000d
        });

        Double[] expectedBuckets = new Double[] {
            10d,
            30d,
            55d,
            200d,
            1000d
        };

        assertArrayEquals(expectedBuckets, buckets.asValues());
    }

    @Test
    public void asDurations() {
        ValueBuckets buckets = new ValueBuckets(new Double[]{
            10d,
            30d,
            55d,
            200d,
            1000d
        });

        Duration[] expectedBuckets = new Duration[]{
            Duration.ofSeconds(10),
            Duration.ofSeconds(30),
            Duration.ofSeconds(55),
            Duration.ofSeconds(200),
            Duration.ofSeconds(1000)
        };

        assertArrayEquals(expectedBuckets, buckets.asDurations());
    }

    @Test
    public void linear() {
        ValueBuckets expectedBuckets = new ValueBuckets(new Double[]{
            2d,
            7d,
            12d,
            17d,
            22d,
            27d,
            32d,
            37d,
            42d,
            47d
        });

        assertEquals(expectedBuckets, ValueBuckets.linear(
            2,
            5,
            10
        ));
    }

    @Test
    public void exponential() {
        ValueBuckets expectedBuckets = new ValueBuckets(new Double[]{
            256d,
            384d,
            576d,
            864d,
            1296d
        });

        assertEquals(expectedBuckets, ValueBuckets.exponential(
            256,
            1.5,
            5
        ));
    }
}
