//
//  CHShoppingCartTableViewCell.m
//  shenbianapp
//
//  Created by book on 2017/11/6.
//  Copyright © 2017 helinkeji. All rights reserved.
//

#import "CHShoppingCartTableViewCell.h"
#import "LXButton.h"
@interface CHShoppingCartTableViewCell ()<UITextFieldDelegate>
@property(nonatomic,strong)UIButton *checkButton;
@property(nonatomic,strong)UIImageView *coverImageView;
@property(nonatomic,strong)UILabel *serviceTitle;
@property(nonatomic,strong)UILabel *serviceContent;
@property(nonatomic,strong)UILabel *priceLabel;
@property(nonatomic,strong)LXButton *addButton;
@property(nonatomic,strong)UIButton *reduceButton;
@property(nonatomic,strong)UITextField *NumTextField;

@end

@implementation CHShoppingCartTableViewCell

- (instancetype)initWithStyle:(UITableViewCellStyle)style reuseIdentifier:(NSString *)reuseIdentifier{
    self = [super initWithStyle:style reuseIdentifier:reuseIdentifier];
    if (self) {
        self.selectionStyle = UITableViewCellSelectionStyleNone;
        [self addSubview:self.checkButton];
        [self.checkButton mas_makeConstraints:^(MASConstraintMaker *make) {
            make.left.equalTo(self).offset(15);
            make.centerY.equalTo(self);
            make.width.height.mas_equalTo(15);
        }];
        
        [self addSubview:self.coverImageView];
        [self.coverImageView mas_makeConstraints:^(MASConstraintMaker *make) {
            make.left.equalTo(self.checkButton.mas_right).offset(20);
            make.top.equalTo(self).offset(20);
            make.width.mas_equalTo(90);
            make.height.mas_equalTo(60);
        }];
        
        [self addSubview:self.serviceTitle];
        [self.serviceTitle mas_makeConstraints:^(MASConstraintMaker *make) {
            make.left.equalTo(self.coverImageView.mas_right).offset(5);
            make.top.equalTo(self.coverImageView);
            make.width.mas_equalTo(120);
            make.height.mas_equalTo(20);
        }];
        
        [self addSubview:self.serviceContent];
        [self.serviceContent mas_makeConstraints:^(MASConstraintMaker *make) {
            make.left.equalTo(self.coverImageView.mas_right).offset(5);
            make.top.equalTo(self.serviceTitle.mas_bottom).offset(5);
            make.width.mas_equalTo(120);
            make.height.mas_equalTo(20);
        }];
        
        [self addSubview:self.priceLabel];
        [self.priceLabel mas_makeConstraints:^(MASConstraintMaker *make) {
            make.left.equalTo(self.coverImageView.mas_right).offset(5);
            make.top.equalTo(self.serviceContent.mas_bottom).offset(5);
            make.width.mas_equalTo(120);
            make.height.mas_equalTo(20);
        }];
        
        [self addSubview:self.addButton];
        [self.addButton mas_makeConstraints:^(MASConstraintMaker *make) {
            make.right.equalTo(self).offset(-15);
            make.top.equalTo(self.priceLabel);
            make.width.height.mas_equalTo(20);
        }];
        
        
        [self addSubview:self.NumTextField];
        [self.NumTextField mas_makeConstraints:^(MASConstraintMaker *make) {
            make.right.equalTo(self.addButton.mas_left).offset(-5);
            make.top.equalTo(self.priceLabel);
            make.width.mas_equalTo(40);
            make.height.mas_equalTo(20);
        }];
        
        [self addSubview:self.reduceButton];
        [self.reduceButton mas_makeConstraints:^(MASConstraintMaker *make) {
            make.right.equalTo(self.NumTextField.mas_left).offset(-5);
            make.top.equalTo(self.priceLabel);
            make.width.height.mas_equalTo(20);
        }];
        
        
    }
    return self;
}

-(UIButton *)checkButton{

    if (_checkButton == nil) {
        _checkButton = [UIButton buttonWithType:(UIButtonTypeCustom)];
        [_checkButton setImage:[UIImage imageNamed:@"gwc_kk"] forState:(UIControlStateNormal)];
        [_checkButton addTarget:self action:@selector(clickCheckButton:) forControlEvents:(UIControlEventTouchUpInside)];
    }
    return _checkButton;
}

- (void)clickCheckButton:(UIButton*)button{

    
}

-(UIImageView *)coverImageView{

    if (_coverImageView == nil) {
        _coverImageView = [[UIImageView alloc]initWithImage:[UIImage imageNamed:@"sy_sj_cover"]];
    }
    return _coverImageView;
}

-(UILabel *)serviceTitle{

    if (_serviceTitle == nil) {
        _serviceTitle = [UILabel new];
        _serviceTitle.text = @"服务标题";
        _serviceTitle.textColor = [UIColor colorWithHexColor:@"#2d333a"];
        _serviceTitle.font = [UIFont systemFontOfSize:15];
        
    }
    return _serviceTitle;
}

-(UILabel *)serviceContent{

    if (_serviceContent == nil) {
        _serviceContent = [UILabel new];
        _serviceContent.text = @"服务描述服务内容";
        _serviceContent.font = [UIFont systemFontOfSize:13];
        _serviceContent.textColor = [UIColor colorWithHexColor:@":#4f5965"];
        
    }
    return _serviceContent;
}

- (UILabel *)priceLabel{

    if (_priceLabel == nil) {
        _priceLabel = [UILabel new];
        _priceLabel.text = @"￥168";
        _priceLabel.textColor = [UIColor colorWithHexColor:@"#df8a7e"];
        _priceLabel.font = [UIFont systemFontOfSize:13];
    }
    return _priceLabel;

}

-(UIButton *)addButton{

    if (_addButton == nil) {
        _addButton = [LXButton buttonWithType:(UIButtonTypeCustom)];
        [_addButton addTarget:self action:@selector(clickAddButton:) forControlEvents:(UIControlEventTouchUpInside)];
        [_addButton setTitle:@"+" forState:(UIControlStateNormal)];
        [_addButton setTitleColor:[UIColor grayColor] forState:(UIControlStateNormal)];
        _addButton.layer.borderWidth = 0.5;
        _addButton.layer.borderColor = [UIColor lightGrayColor].CGColor;

    }
    return _addButton;
}

- (void)clickAddButton:(UIButton*)button{
    NSInteger count =  self.NumTextField.text.integerValue;
    count++;
    if (count > 100) {
        count = 100;
    }
    self.NumTextField.text = [NSString stringWithFormat:@"%ld",count] ;

}

-(UITextField *)NumTextField{

    if (_NumTextField == nil) {
        _NumTextField = [[UITextField alloc]init];
        _NumTextField.keyboardType = UIKeyboardTypePhonePad;
        _NumTextField.layer.borderWidth = 0.5;
        _NumTextField.layer.borderColor = [UIColor lightGrayColor].CGColor;
        _NumTextField.textAlignment = NSTextAlignmentCenter;
        _NumTextField.delegate = self;
        _NumTextField.text = @"1";
    }
    return _NumTextField;
}

-(UIButton *)reduceButton{
    if (_reduceButton == nil) {
        _reduceButton = [LXButton buttonWithType:(UIButtonTypeCustom)];
        [_reduceButton addTarget:self action:@selector(clickReduceButton:) forControlEvents:(UIControlEventTouchUpInside)];
        [_reduceButton setTitle:@"-" forState:(UIControlStateNormal)];
        [_reduceButton setTitleColor:[UIColor grayColor] forState:(UIControlStateNormal)];
        _reduceButton.layer.borderWidth = 0.5;
        _reduceButton.layer.borderColor = [UIColor lightGrayColor].CGColor;
    }
    return _reduceButton;
}

- (void)clickReduceButton:(UIButton*)button{
    NSInteger count =  self.NumTextField.text.integerValue;
    count--;
    if (count < 1) {
        count = 1;
    }
    self.NumTextField.text = [NSString stringWithFormat:@"%ld",count] ;

}

-(void)textFieldDidEndEditing:(UITextField *)textField{
    NSString *text = textField.text;
    if ([text integerValue] > 100) {
        self.NumTextField.text = @"100";
    } else if ([text integerValue] < 1){
        self.NumTextField.text = @"1";
        
    }
    
}

-(void)setOrderModel:(CHOrderModel *)orderModel{
    
    self.serviceTitle.text = orderModel.serviceTitle;
    self.priceLabel.text = orderModel.servicePrice;
    self.NumTextField.text = [NSString stringWithFormat:@"%@",orderModel.serviceAmount];
    
}

@end
