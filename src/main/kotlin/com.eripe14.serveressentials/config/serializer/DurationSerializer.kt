package com.eripe14.serveressentials.config.serializer

import eu.okaeri.configs.schema.GenericsDeclaration
import eu.okaeri.configs.serdes.DeserializationData
import eu.okaeri.configs.serdes.ObjectSerializer
import eu.okaeri.configs.serdes.SerializationData
import java.time.Duration

class DurationSerializer : ObjectSerializer<Duration> {
    override fun supports(type: Class<in Duration>): Boolean {
        return Duration::class.java.isAssignableFrom(type)
    }

    override fun serialize(duration: Duration, data: SerializationData, generics: GenericsDeclaration) {
        data.add("duration", duration.toString())
    }

    override fun deserialize(data: DeserializationData, generics: GenericsDeclaration): Duration {
        return Duration.parse(data.get("duration", String::class.java))
    }
}