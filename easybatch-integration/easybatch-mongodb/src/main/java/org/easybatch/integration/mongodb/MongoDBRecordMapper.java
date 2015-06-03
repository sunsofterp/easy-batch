/*
 *  The MIT License
 *
 *   Copyright (c) 2015, Mahmoud Ben Hassine (mahmoud@benhassine.fr)
 *
 *   Permission is hereby granted, free of charge, to any person obtaining a copy
 *   of this software and associated documentation files (the "Software"), to deal
 *   in the Software without restriction, including without limitation the rights
 *   to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *   copies of the Software, and to permit persons to whom the Software is
 *   furnished to do so, subject to the following conditions:
 *
 *   The above copyright notice and this permission notice shall be included in
 *   all copies or substantial portions of the Software.
 *
 *   THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *   IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *   FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *   AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *   LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *   OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 *   THE SOFTWARE.
 */

package org.easybatch.integration.mongodb;

import com.mongodb.DBObject;
import org.easybatch.core.api.Record;
import org.easybatch.core.api.RecordMapper;
import org.mongodb.morphia.Morphia;

/**
 * A mapper that maps Mongo {@link com.mongodb.DBObject} to domain objects using
 * <a href="https://github.com/mongodb/morphia">Morphia</a>.
 *
 * @author Mahmoud Ben Hassine (mahmoud@benhassine.fr)
 */
public class MongoDBRecordMapper<T> implements RecordMapper<T> {

    private Morphia morphia;

    private Class<T> type;

    public MongoDBRecordMapper(Class<T> type) {
        this.type = type;
        this.morphia = new Morphia();
        this.morphia.map(type);
    }

    @Override
    public T mapRecord(Record record) throws Exception {
        MongoDBRecord mongoDBRecord = (MongoDBRecord) record;
        DBObject dbObject = mongoDBRecord.getPayload();
        return morphia.fromDBObject(type, dbObject);
    }

}
