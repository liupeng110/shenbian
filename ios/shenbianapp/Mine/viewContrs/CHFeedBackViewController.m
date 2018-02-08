//
//  CHFeedBackViewController.m
//  shenbianapp
//
//  Created by book on 2018/2/3.
//  Copyright © 2018年 helinkeji. All rights reserved.
//

#import "CHFeedBackViewController.h"

@interface CHFeedBackViewController ()
@property(nonatomic,strong)IQTextView *textView;
@property(nonatomic,strong)UIButton *commitButton;
@end

@implementation CHFeedBackViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.
    self.title = @"意见反馈";
    [self.view addSubview:self.textView];
    [self.textView mas_makeConstraints:^(MASConstraintMaker *make) {
        make.left.equalTo(self.view).offset(15);
        make.left.top.equalTo(self.view).offset(79);

        make.right.equalTo(self.view).offset(-15);
        make.height.mas_equalTo(300);
    }];
    [self.view addSubview:self.commitButton];
    [self.commitButton mas_makeConstraints:^(MASConstraintMaker *make) {
        make.centerX.equalTo(self.view);
        make.top.equalTo(self.textView.mas_bottom).offset(20);
        make.width.mas_equalTo(80);
        make.height.mas_equalTo(30);
    }];
}

-(UITextView *)textView{
    if (_textView == nil) {
        _textView = [IQTextView new];
        _textView.placeholder = @"请输入您的反馈意见";
        _textView.layer.borderWidth = 2;
        _textView.layer.borderColor = [UIColor lightGrayColor].CGColor;
        _textView.layer.cornerRadius = 5;
    }
    return _textView;
}

-(UIButton *)commitButton{
    if (!_commitButton) {
        _commitButton = [UIButton buttonWithType:(UIButtonTypeCustom)];
        [_commitButton setTitle:@"提交" forState:(UIControlStateNormal)];
        _commitButton.backgroundColor = [UIColor grayColor];
        _commitButton.layer.cornerRadius = 3;
        [_commitButton addTarget:self action:@selector(clickCommitButton) forControlEvents:(UIControlEventTouchDown)];
    }
    return _commitButton;
}

-(void)clickCommitButton{
    
    if (self.textView.text.length > 5) {
        UIAlertView *alert = [[UIAlertView alloc]initWithTitle:@"温馨提示" message:@"您已提交成功" delegate:nil cancelButtonTitle:@"知晓" otherButtonTitles:nil];
        [alert show];
    }
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

/*
#pragma mark - Navigation

// In a storyboard-based application, you will often want to do a little preparation before navigation
- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender {
    // Get the new view controller using [segue destinationViewController].
    // Pass the selected object to the new view controller.
}
*/

@end
