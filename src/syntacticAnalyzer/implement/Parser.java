package syntacticAnalyzer.implement;

import grammerParser.LexicalAnalyzer;
import grammerParser.TextProcessor;

import javax.swing.table.DefaultTableModel;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class Parser {
	
	

	public static void main(String[] args) {
		Parser parser = new Parser();
		parser.parseByFile("src/grammerParser/file/sample.txt");
	}
	
	public void parseByFile(String fileName1) {
		StringBuilder ta_input  = new StringBuilder();
		File file = new File(fileName1);
		try{
			InputStream in = new FileInputStream(file);
			int tempbyte;
			while ((tempbyte=in.read()) != -1) {
				ta_input.append(""+(char)tempbyte);
			}
			in.close();
		}
		catch(Exception event){
			event.printStackTrace();
		}
		LexicalAnalyzer text_lex = new LexicalAnalyzer(ta_input.toString());
		text_lex.scannerAll();
		
		ArrayList<String> lex_result_stack = text_lex.get_Lex_Result();
		ArrayList<HashMap<String, String>> lex_error_stack = text_lex.get_Lex_Error();
		
		// 若是存在词法分析错误
		if(lex_error_stack.size()!=0){
			System.out.println("存在词法分析错误");
		}
		else {
			// 句法分析
			TextProcessor textParse = new TextProcessor(lex_result_stack, null);
			textParse.Parsing();
		}
	}
}
