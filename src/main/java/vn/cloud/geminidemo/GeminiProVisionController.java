package vn.cloud.geminidemo;

import com.google.cloud.vertexai.api.GenerateContentResponse;
import com.google.cloud.vertexai.generativeai.preview.ContentMaker;
import com.google.cloud.vertexai.generativeai.preview.GenerativeModel;
import com.google.cloud.vertexai.generativeai.preview.PartMaker;
import com.google.cloud.vertexai.generativeai.preview.ResponseHandler;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/gemini-pro-vision")
public class GeminiProVisionController {

    private final GenerativeModel generativeModel;

    public GeminiProVisionController(@Qualifier("geminiProVisionGenerativeModel") GenerativeModel generativeModel) {
        this.generativeModel = generativeModel;
    }

    @PostMapping
    public String file(@RequestParam("file") MultipartFile file, @RequestParam String question) throws IOException {
        GenerateContentResponse generateContentResponse = this.generativeModel.generateContent(
                ContentMaker.fromMultiModalData(
                        PartMaker.fromMimeTypeAndData(file.getContentType(), file.getBytes()),
                        question
                )
        );
        return ResponseHandler.getText(generateContentResponse);
    }
}
