package ava.client.utilities;

import ava.client.models.ManifestItemV3;
import ava.client.models.ManifestV3;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ManifestHelper {

    private static final List<String> EMPTY_STRING_LIST = Collections.emptyList();
    private static final List<List<String>> EMPTY_NESTED_STRING_LIST = Collections.emptyList();

    public static ManifestV3 minifyManifest(ManifestV3 manifest) {
        Map<String, ManifestItemV3> resultMap = new HashMap<>(manifest.getCs().size());
        for (Map.Entry<String, ManifestItemV3> entry : manifest.getCs().entrySet()) {
            ManifestItemV3 value = entry.getValue();
            resultMap.put(entry.getKey(), new ManifestItemV3(
                    value.getP(),
                    (value.getS() != null && !value.getS().isEmpty()) ? value.getS() : null,
                    (value.getA() != null && !value.getA().isEmpty()) ? value.getA() : null,
                    value.getC()
            ));
        }
        return new ManifestV3(resultMap, manifest.getMs(), manifest.getH(), manifest.getGh(), manifest.getT());
    }

    public static ManifestV3 originalManifest(ManifestV3 manifest) {
        Map<String, ManifestItemV3> resultMap = new HashMap<>(manifest.getCs().size());
        for (Map.Entry<String, ManifestItemV3> entry : manifest.getCs().entrySet()) {
            ManifestItemV3 value = entry.getValue();
            if (value != null && (value.getS() == null || value.getA() == null)) {
                resultMap.put(entry.getKey(), new ManifestItemV3(
                        value.getP(),
                        getNonNullStringList(value.getS()),
                        getNonNullNestedStringList(value.getA()),
                        value.getC()
                ));
            } else {
                resultMap.put(entry.getKey(), value);
            }
        }
        return new ManifestV3(resultMap, manifest.getMs(), manifest.getH(), manifest.getGh(), manifest.getT());
    }

//    public static ProtoManifestV3 toProto(ManifestV3 source) {
//        ProtoManifestV3 target = new ProtoManifestV3();
//        target.setH(source.getH() != null ? source.getH() : "");
//        target.setGh(source.getGh() != null ? source.getGh() : "");
//        target.setT(source.getT() != null ? source.getT() : "");
//        for (Map.Entry<String, ManifestItemV3> entry : source.getCs().entrySet()) {
//            target.getCs().put(entry.getKey(), toProto(entry.getValue()));
//        }
//        target.getMs().addAll(source.getMs());
//        return target;
//    }
//
//    public static ProtoManifestItemV3 toProto(ManifestItemV3 source) {
//        ProtoManifestItemV3 target = new ProtoManifestItemV3();
//        target.setP(source.getP() != null ? source.getP() : "");
//        target.setC(source.getC());
//        target.getS().addAll(source.getS() != null ? source.getS() : new ArrayList<>());
//        if (source.getA() != null) {
//            for (List<String> group : source.getA()) {
//                ProtoABTestGroup protoGroup = new ProtoABTestGroup();
//                protoGroup.getGroups().addAll(group);
//                target.getA().add(protoGroup);
//            }
//        }
//        return target;
//    }

    // optimized version: modifies manifest in-place
    public static ManifestV3 originalManifestInPlace(ManifestV3 manifest) {
        for (ManifestItemV3 item : manifest.getCs().values()) {
            if (item.getS() == null) item.setS(EMPTY_STRING_LIST);
            if (item.getA() == null) item.setA(EMPTY_NESTED_STRING_LIST);
        }
        return manifest;
    }

    private static List<String> getNonNullStringList(List<String> source) {
        if (source != null && !source.isEmpty()) {
            return source;
        }
        return EMPTY_STRING_LIST;
    }

    private static List<List<String>> getNonNullNestedStringList(List<List<String>> source) {
        if (source != null && !source.isEmpty()) {
            return source;
        }
        return EMPTY_NESTED_STRING_LIST;
    }
}
