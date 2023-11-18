package com.mangkyu.stream.Quiz1;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.opencsv.CSVReader;

public class Quiz1 {

	// 이름, 취미, 소개

	// 1.1 각 취미를 선호하는 인원이 몇 명인지 계산하여라.
	public Map<String, Integer> quiz1() throws IOException {
		List<String[]> csvLines = readCsvLines();

		return csvLines.stream()
			.map(v -> v[1])
			.flatMap(v -> Stream.of(v.split(":")))
			.map(String::trim)
			.collect(Collectors.groupingBy(s -> s))
			.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, i2 -> i2.getValue().size()));
	}

	// 1.2 각 취미를 선호하는 정씨 성을 갖는 인원이 몇 명인지 계산하여라.
	public Map<String, Integer> quiz2() throws IOException {
		List<String[]> csvLines = readCsvLines();
		return csvLines.stream()
			.filter(v -> v[0].trim().startsWith("정"))
			.map(v -> v[1])
			.flatMap(v -> Stream.of(v.split(":")))
			.map(String::trim)
			.collect(Collectors.groupingBy(s -> s))
			.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, i2 -> i2.getValue().size()));
	}

	// 1.3 소개 내용에 '좋아'가 몇번 등장하는지 계산하여라.
	public int quiz3() throws IOException {
		List<String[]> csvLines = readCsvLines();
		return csvLines.stream()
			.map(v -> v[2])
			.map(String::trim)
			.map(v -> "힝" + v + "힝")
			.map(s -> s.split("좋아").length - 1)
			.reduce(0, Integer::sum);
	}

	private List<String[]> readCsvLines() throws IOException {
		CSVReader csvReader = new CSVReader(new FileReader(getClass().getResource("/user.csv").getFile()));
		csvReader.readNext();
		return csvReader.readAll();
	}

}
