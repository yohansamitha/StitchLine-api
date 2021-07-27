package com.stitchline.stitchlineapi.util

class StandardResponse(var code: String?, var message: String?, var data: Any?) {
    override fun toString(): String {
        return "StandardResponse{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}'
    }
}
